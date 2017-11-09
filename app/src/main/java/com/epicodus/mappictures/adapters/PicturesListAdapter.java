package com.epicodus.mappictures.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.epicodus.mappictures.R;
import com.epicodus.mappictures.models.Picture;
import com.epicodus.mappictures.ui.DetailPictureActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by katsiarynamashokha on 11/8/17.
 */

public class PicturesListAdapter extends RecyclerView.Adapter<PicturesListAdapter.PicturesViewHolder>{
    private ArrayList<Picture> mPictures = new ArrayList<>();
    private Context mContext;
    private static final int MAX_WIDTH = 500;
    private static final int MAX_HEIGHT = 500;

    public PicturesListAdapter(ArrayList<Picture> pictures, Context context) {
        mPictures = pictures;
        mContext = context;
    }

    @Override
    public PicturesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singular_picture_view, parent, false);
        PicturesViewHolder viewHolder = new PicturesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PicturesViewHolder holder, int position) {
        holder.bindPictures(mPictures.get(position));
    }

    @Override
    public int getItemCount() {
        return mPictures.size();
    }

    public class PicturesViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private Context mContext;

        public PicturesViewHolder(final View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mImageView = itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Picture clickedPicture = mPictures.get(itemPosition);
                    Intent intent = new Intent(mContext, DetailPictureActivity.class);
                    intent.putExtra("clickedPhoto", Parcels.wrap(clickedPicture));
                    mContext.startActivity(intent);
                }
            });
        }

        public void bindPictures(Picture picture) {
            Picasso.with(mContext)
                    .load(picture.getUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .into(mImageView);
        }
    }
}
