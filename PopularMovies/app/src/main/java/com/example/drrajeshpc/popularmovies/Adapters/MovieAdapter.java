package com.example.drrajeshpc.popularmovies.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.drrajeshpc.popularmovies.Models.Movie;
import com.example.drrajeshpc.popularmovies.Network.FetchUrl;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Dr. Rajesh PC on 16-10-2016.
 */

public class MovieAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Movie> mMovieArrayList;
    public MovieAdapter(Context c, ArrayList<Movie>movieArrayList) {
        mContext = c;
        mMovieArrayList = movieArrayList;
    }
    @Override
    public int getCount() {
       return mMovieArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return mMovieArrayList.get(i);
    }

    // TODO: What to return from here.
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setLayoutParams(new GridView.MarginLayoutParams(GridView.MarginLayoutParams.WRAP_CONTENT, GridView.MarginLayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            //imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView)view;
        }
        URL posterUrl = FetchUrl.getPosterUrl("w185", mMovieArrayList.get(i).getmPosterPath());
        Picasso.with(mContext).load(posterUrl.toString()).into(imageView);
        return imageView;
    }

    public void setMoviesArrayList(ArrayList<Movie> movieArrayList) {
        mMovieArrayList = movieArrayList;
    }
}
