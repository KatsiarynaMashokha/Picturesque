package com.epicodus.mappictures.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.epicodus.mappictures.R;
import com.epicodus.mappictures.models.Picture;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DetailPictureActivity extends AppCompatActivity {
    ArrayList<Picture> mPictures = new ArrayList<>();
    private Picture mPicture;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_picture);
        mImageView = (ImageView) findViewById(R.id.detail_image_view);
        mPicture = Parcels.unwrap(getIntent().getParcelableExtra("clickedPhoto"));
        Picasso.with(this)
                .load(mPicture.getUrl())
                .resize(600, 1000)
                .into(mImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Check this out: " + mPicture.getUrl();
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
