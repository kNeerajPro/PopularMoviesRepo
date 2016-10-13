package com.example.drrajeshpc.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.drrajeshpc.popularmovies.Network.FetchUrl;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FetchUrl.getPopularMoviesUrl("1");
        FetchUrl.getTopRatedMoviesUrl("1");
        FetchUrl.getPosterUrl("w185","/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");
    }
}
