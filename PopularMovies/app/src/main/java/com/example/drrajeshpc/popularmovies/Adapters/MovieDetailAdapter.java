package com.example.drrajeshpc.popularmovies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drrajeshpc.popularmovies.Constants.StringConstants;
import com.example.drrajeshpc.popularmovies.Models.Movie;
import com.example.drrajeshpc.popularmovies.Network.FetchUrl;
import com.example.drrajeshpc.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.net.URL;

/**
 * Created by Dr. Rajesh PC on 17-10-2016.
 */

public class MovieDetailAdapter extends BaseAdapter {
    private Context mContext;
    private Movie mMovie;
    public MovieDetailAdapter(Context context, Movie movie) {
        mContext = context;
        mMovie = movie;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return mMovie;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int viewType = getItemViewType(i);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            if (viewType == 0) {
                view = inflater.inflate(R.layout.movie_detail_header, viewGroup, false);
            } else {
                view = inflater.inflate(R.layout.movie_detail, viewGroup, false);
            }
        }

        if (viewType == 0) {
            TextView movieNameTextView = (TextView) view.findViewById(R.id.textView);
            movieNameTextView.setText(mMovie.getmTitle());
        } else {
            ImageView moviePosterImageView = (ImageView) view.findViewById(R.id.imageView2);
            URL posterUrl = FetchUrl.getPosterUrl(StringConstants.POSTER_IMAGE_SIZE, mMovie.getmPosterPath());
            Picasso.with(mContext).load(posterUrl.toString()).into(moviePosterImageView);
            TextView dateTextView = (TextView) view.findViewById(R.id.textView2);
            dateTextView.setText(mMovie.getmReleaseDate());
            TextView avgRatingTextView = (TextView) view.findViewById(R.id.textView3);
            avgRatingTextView.setText(mMovie.getmAverageVote());
            Button favouriteButton = (Button) view.findViewById(R.id.button);
            favouriteButton.setText(R.string.set_as_favourite);
            TextView synopsisTextView = (TextView) view.findViewById(R.id.textView5);
            synopsisTextView.setText(mMovie.getmOverview());
        }
        return view;
    }

}
