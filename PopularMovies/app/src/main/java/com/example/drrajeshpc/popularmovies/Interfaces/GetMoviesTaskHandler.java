package com.example.drrajeshpc.popularmovies.Interfaces;

import com.example.drrajeshpc.popularmovies.Models.Movie;

import java.util.ArrayList;

/**
 * Created by Dr. Rajesh PC on 18-10-2016.
 */

public interface GetMoviesTaskHandler {
    public void moviesList(ArrayList<Movie> moviesArrayList);
}