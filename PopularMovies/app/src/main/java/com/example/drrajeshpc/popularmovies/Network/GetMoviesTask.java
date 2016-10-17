package com.example.drrajeshpc.popularmovies.Network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.drrajeshpc.popularmovies.Constants.MoviesOrder;
import com.example.drrajeshpc.popularmovies.MainActivity;
import com.example.drrajeshpc.popularmovies.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Dr. Rajesh PC on 15-10-2016.
 */

public class GetMoviesTask extends AsyncTask<String, Void, String> {
    WeakReference<MainActivity> weakDelegate;
    private final String LOG_TAG = GetMoviesTask.class.getSimpleName();
    private MoviesOrder movieOrder = MoviesOrder.TOP_RATED;
    public GetMoviesTask(MoviesOrder moviesOrder, MainActivity activity) {
        this.movieOrder = moviesOrder;
        weakDelegate = new WeakReference<MainActivity>(activity);
    }

    public void setMovieOrder(MoviesOrder newMovieOrder) {
        this.movieOrder = newMovieOrder;
    }

    @Override
    protected String doInBackground(String... strings) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String jsonStr = null;

        try {
            URL url;
            if (movieOrder == MoviesOrder.POPULAR) {
                url =  FetchUrl.getPopularMoviesUrl("1");
            } else {
                url = FetchUrl.getTopRatedMoviesUrl("1");
            }
            Log.d(LOG_TAG, url.toString());
            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return jsonStr;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {
            ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray moviesArray = jsonObject.getJSONArray("results");
                for(int i = 0; i < moviesArray.length(); i++) {
                    JSONObject movieObject = moviesArray.getJSONObject(i);
                    Movie movie = new Movie(movieObject);
                    if (movie != null) {
                        movieArrayList.add(movie);
                    }
                }
                // TODO: Use Interface and make this AsyncTask generic.
                MainActivity activity = weakDelegate.get();
                activity.moviesList(movieArrayList);
                Log.d(LOG_TAG, movieArrayList.toString());
            } catch (JSONException e) {
                Log.d(LOG_TAG, "Error parsing movie json");
            }
        } else {
            Log.d(LOG_TAG, "No result from getMovies task");
        }
    }
}
