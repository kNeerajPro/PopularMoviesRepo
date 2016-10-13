package com.example.drrajeshpc.popularmovies.Network;

import android.net.Uri;
import android.util.Log;

import com.example.drrajeshpc.popularmovies.BuildConfig;
import com.example.drrajeshpc.popularmovies.Constants.StringConstants;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dr. Rajesh PC on 14-10-2016.
 */

public class FetchUrl {
    // Base URLs
    private static final String BASE_URL_MOVIEDB = "http://api.themoviedb.org/3/movie/";
    private static final String BASE_URL_IMAGE = " http://image.tmdb.org/t/p/";

    private static final String POPULAR_MOVIE_KEY = "popular";
    private static final String TOP_RATED_MOVIE_KEY = "top_rated";

    public static final URL getPopularMoviesUrl(String pageNumber) {
        Uri builtUri = Uri.parse(BASE_URL_MOVIEDB).buildUpon()
                .appendPath(POPULAR_MOVIE_KEY)
                .appendQueryParameter(StringConstants.API_KEY, BuildConfig.MOVIE_DB_API_KEY)
                .appendQueryParameter(StringConstants.PAGE, pageNumber)
                .build();
        Log.d(StringConstants.DEBUG_LOG_CONSTANT, builtUri.toString());
        URL url= null;
        try {
           url = new URL(builtUri.toString());
        }catch (MalformedURLException e) {
            Log.d(StringConstants.DEBUG_LOG_CONSTANT, "Malformed exception when building url = " + url.toString());
        }
        return url;
    }

    public static final URL getTopRatedMoviesUrl(String pageNumber) {
        Uri builtUri = Uri.parse(BASE_URL_MOVIEDB).buildUpon()
                .appendPath(TOP_RATED_MOVIE_KEY)
                .appendQueryParameter(StringConstants.API_KEY, BuildConfig.MOVIE_DB_API_KEY)
                .appendQueryParameter(StringConstants.PAGE, pageNumber)
                .build();
        Log.d(StringConstants.DEBUG_LOG_CONSTANT, builtUri.toString());
        URL url= null;
        try {
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e) {
            Log.d(StringConstants.DEBUG_LOG_CONSTANT, "Malformed exception when building url = " + url.toString());
        }
        return url;
    }

    public static final URL getPosterUrl(String posterSize, String posterPath) {
        Uri builtUri = Uri.parse(BASE_URL_IMAGE).buildUpon()
                .appendPath(posterSize)
                .appendEncodedPath(posterPath)
                .build();
        Log.d(StringConstants.DEBUG_LOG_CONSTANT, builtUri.toString());
        URL url= null;
        try {
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e) {
            Log.d(StringConstants.DEBUG_LOG_CONSTANT, "Malformed exception when building url = " + url.toString());
        }
        return url;
    }
}
