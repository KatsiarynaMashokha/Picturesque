package com.epicodus.mappictures.ui;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.mappictures.R;
import com.epicodus.mappictures.models.Picture;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetailPictureActivity extends AppCompatActivity
        implements View.OnClickListener{
    private Picture mPicture;
    private ImageView mImageView;
    private TextView mTitle;
    private ImageButton mDownloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_picture);
        mImageView = (ImageView) findViewById(R.id.detail_image_view);
        mTitle = (TextView) findViewById(R.id.title);
        mDownloadButton = (ImageButton) findViewById(R.id.download_image_view);
        mDownloadButton.setOnClickListener(this);
        mPicture = Parcels.unwrap(getIntent().getParcelableExtra("clickedPhoto"));
        Picasso.with(this)
                .load(mPicture.getUrl())
                .resize(800, 800)
                .into(mImageView);
        mTitle.setText(mPicture.getTitle());
        mTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/grandhotel.ttf"));
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

    @Override
    public void onClick(View v) {
        if(v == mDownloadButton) {
            String savedPath = saveToInternalStorage(mImageView);
            Toast.makeText(this, "Saved to " + savedPath, Toast.LENGTH_LONG).show();
        }
    }

    private String saveToInternalStorage(ImageView imageView) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory =  cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, mTitle.getText().toString().replaceAll(" ",""));
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            imageView.buildDrawingCache();
            Bitmap bmap = imageView.getDrawingCache();
            bmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
