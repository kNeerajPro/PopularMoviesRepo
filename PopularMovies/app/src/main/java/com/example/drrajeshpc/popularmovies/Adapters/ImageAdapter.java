package com.example.drrajeshpc.popularmovies.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Dr. Rajesh PC on 14-10-2016.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView)view;
        }
        Picasso.with(mContext).load(mThumbIds[i]).into(imageView);
        //Picasso.with(mContext).setLoggingEnabled(true);
        return imageView;
    }

    private String[] mThumbIds = {
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png"
    };
}
