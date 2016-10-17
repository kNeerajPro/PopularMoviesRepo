package com.example.drrajeshpc.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.drrajeshpc.popularmovies.Adapters.MovieAdapter;
import com.example.drrajeshpc.popularmovies.Constants.MoviesOrder;
import com.example.drrajeshpc.popularmovies.Models.Movie;
import com.example.drrajeshpc.popularmovies.Network.GetMoviesTask;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
    private MainActivityFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            mainFragment = new MainActivityFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, mainFragment, "MainFragment")
                    .commit();
        } else {
            mainFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
        }
        if (savedInstanceState != null &&  savedInstanceState.containsKey("com.app.movieslist") == true) {
            ArrayList<Movie> savedMoviesList = savedInstanceState.getParcelableArrayList("com.app.movieslist");
            this.moviesList(savedMoviesList);
        } else {
            new GetMoviesTask(MoviesOrder.POPULAR, this).execute();
        }
        // Test Code
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_top_rated) {
            new GetMoviesTask(MoviesOrder.TOP_RATED, this).execute();
            return true;
        } else if (id == R.id.action_popular) {
            new GetMoviesTask(MoviesOrder.POPULAR, this).execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void moviesList(ArrayList<Movie> moviesArrayList) {
        this.movieArrayList = moviesArrayList;
        if (mainFragment.mMovieAdapter != null ) {
            mainFragment.mMovieAdapter.setMoviesArrayList(this.movieArrayList);
            mainFragment.mMovieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("com.app.movieslist", this.movieArrayList);
        super.onSaveInstanceState(outState);
    }

    // Fragment for the main grid view.
    public static class MainActivityFragment extends Fragment {
        private MovieAdapter mMovieAdapter;
        public MainActivityFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
             // TODO: Inflate the rootview.
            View rootView = inflater.inflate(R.layout.fragment_main_activity, container, false);
            setupView(rootView);
            return rootView;
        }

        void setupView(View rootView) {
            GridView view = (GridView)rootView.findViewById(R.id.gridview);
            view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Movie movie = (Movie)mMovieAdapter.getItem(position);
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class)
                            .putExtra("com.app.models.movie", movie);
                    startActivity(intent);
                }
            });
            MainActivity mainActivity = (MainActivity) getActivity();
            mMovieAdapter = new MovieAdapter(getContext(), mainActivity.movieArrayList);
            view.setAdapter(mMovieAdapter);
        }
    }
}
