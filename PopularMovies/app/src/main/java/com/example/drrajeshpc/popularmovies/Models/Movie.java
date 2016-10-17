package com.example.drrajeshpc.popularmovies.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.drrajeshpc.popularmovies.Constants.StringConstants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dr. Rajesh PC on 15-10-2016.
 */

public class Movie implements Parcelable{
    private String mId;
    private String mTitle;
    private String mPosterPath;
    private String mOverview;
    private String mAverageVote;
    private String mReleaseDate;

    public String getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmOverview() {
        return mOverview;
    }

    public String getmAverageVote() {
        return mAverageVote;
    }

    public void setmAverageVote(String mAverageVote) {
        this.mAverageVote = mAverageVote;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public Movie(JSONObject movieObject) {
        try {
            mId = movieObject.getString(StringConstants.KEY_MOVIE_ID);
            mTitle = movieObject.getString(StringConstants.KEY_ORIGINAL_TITLE);
            mPosterPath = movieObject.getString(StringConstants.KEY_POSTER_PATH);
            mOverview = movieObject.getString(StringConstants.KEY_OVERVIEW);
            mAverageVote = movieObject.getString(StringConstants.KEY_VOTE_AVERAGE) + "/10";
            mReleaseDate = movieObject.getString(StringConstants.KEY_RELEASE_DATE);
        } catch (JSONException e) {
            Log.d(Movie.class.getSimpleName(), "Exception while creating object with id : " + mId);
        } finally {
            // If id is null we won't add it in the list of movies.
            mId = null;
        }
    }

    private Movie(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mPosterPath = in.readString();
        mOverview = in.readString();
        mAverageVote = in.readString();
        mReleaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mTitle);
        dest.writeString(mPosterPath);
        dest.writeString(mOverview);
        dest.writeString(mAverageVote);
        dest.writeString(mReleaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
