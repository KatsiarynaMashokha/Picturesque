package com.epicodus.mappictures.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.epicodus.mappictures.R;
import com.epicodus.mappictures.Services.FlickrService;
import com.epicodus.mappictures.adapters.PicturesListAdapter;
import com.epicodus.mappictures.models.Picture;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PicturesActivity extends AppCompatActivity {
    private ArrayList<Picture> mPictures;
    private RecyclerView mRecyclerView;
    private TextView mNoResultsTextView;
    private PicturesListAdapter mAdapter;
    private static final String TAG = PicturesActivity.class.getSimpleName();
    private String lat;
    private String lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictures_activity);

        mRecyclerView = (RecyclerView) findViewById(R.id.listView);
        mNoResultsTextView = (TextView) findViewById(R.id.no_results_text);
        lat = getIntent().getStringExtra("LAT");
        lon = getIntent().getStringExtra("LON");
        getPictures();
    }

    private void getPictures() {
        final FlickrService flickrService = new FlickrService();
        flickrService.findPictures(lat, lon, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mPictures = flickrService.processPictures(response);

                PicturesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new PicturesListAdapter(mPictures, getApplicationContext());
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new GridLayoutManager(PicturesActivity.this, 2);
                        mRecyclerView.setLayoutManager(layoutManager);
                        if(mPictures.size() == 0) {
                            mNoResultsTextView.setText("No pictures found for this location");
                            mNoResultsTextView.setTextSize(20f);
                        }
                    }
                });
            }
        });
    }
}
