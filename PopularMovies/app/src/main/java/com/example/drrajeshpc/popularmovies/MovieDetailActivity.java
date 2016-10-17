package com.example.drrajeshpc.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.drrajeshpc.popularmovies.Adapters.MovieDetailAdapter;
import com.example.drrajeshpc.popularmovies.Models.Movie;

import static com.example.drrajeshpc.popularmovies.Constants.StringConstants.KEY_INTENT_EXTRA_STRING_KEY_MOVIE;

public class MovieDetailActivity extends AppCompatActivity {
    private Movie mMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Bundle bundle = getIntent().getExtras();
        mMovie = bundle.getParcelable(KEY_INTENT_EXTRA_STRING_KEY_MOVIE);
        getSupportActionBar().setTitle(mMovie.getmTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MovieDetailAdapter movieDetailAdapter = new MovieDetailAdapter(this, mMovie);
        ListView listView = (ListView)findViewById(R.id.movie_detail_list_view);
        listView.setAdapter(movieDetailAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
