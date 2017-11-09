package com.epicodus.mappictures.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.mappictures.R;
import com.epicodus.mappictures.Services.FlickrService;
import com.epicodus.mappictures.models.Picture;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PicturesActivity extends AppCompatActivity {
    private ArrayList<Picture> mPictures;
    private ListView mListView;
    private static final String TAG = PicturesActivity.class.getSimpleName();
    private String lat;
    private String lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictures_activity);

        mListView = (ListView) findViewById(R.id.listView);
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
                        String[] picrls = new String[mPictures.size()];
                        for (int i = 0; i < picrls.length; i++) {
                            picrls[i] = mPictures.get(i).getUrl();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(PicturesActivity.this,
                                android.R.layout.simple_list_item_1, picrls);
                        mListView.setAdapter(adapter);

                    }
                });
            }
        });
    }
}
