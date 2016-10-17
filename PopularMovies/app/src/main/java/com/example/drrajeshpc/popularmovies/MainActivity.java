package com.example.drrajeshpc.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new MainActivityFragment(), "MainFragment")
                    .commit();
        }
    }

    // Fragment for the main grid view.
    public static class MainActivityFragment extends Fragment {
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        private MovieAdapter mMovieAdapter;
        private int selectedMenuItemId;
        public MainActivityFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
            mMovieAdapter = new MovieAdapter(getContext(), this.movieArrayList);
            if (savedInstanceState != null &&  savedInstanceState.containsKey("com.app.movieslist") == true) {
                ArrayList<Movie> savedMoviesList = savedInstanceState.getParcelableArrayList("com.app.movieslist");
                this.moviesList(savedMoviesList);
                selectedMenuItemId = savedInstanceState.getInt("com.app.menuitem");
            } else {
                selectedMenuItemId = R.id.action_popular;
                new GetMoviesTask(MoviesOrder.POPULAR, this).execute();
            }
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.main_activity_menu, menu);
        }

        @Override
        public void onPrepareOptionsMenu(Menu menu) {
            MenuItem menuItem = menu.findItem(selectedMenuItemId);
            menuItem.setChecked(true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            selectedMenuItemId = item.getItemId();
            int id = item.getItemId();
            item.setChecked(true);
            if (id == R.id.action_top_rated) {
                new GetMoviesTask(MoviesOrder.TOP_RATED, this).execute();
                return true;
            } else if (id == R.id.action_popular) {
                new GetMoviesTask(MoviesOrder.POPULAR, this).execute();
                return true;
            }
            return super.onOptionsItemSelected(item);
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
            view.setAdapter(mMovieAdapter);
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            outState.putParcelableArrayList("com.app.movieslist", this.movieArrayList);
            outState.putInt("com.app.menuitem", this.selectedMenuItemId);
            super.onSaveInstanceState(outState);
        }

        public void moviesList(ArrayList<Movie> moviesArrayList) {
            this.movieArrayList = moviesArrayList;
            this.mMovieAdapter.setMoviesArrayList(this.movieArrayList);
            this.mMovieAdapter.notifyDataSetChanged();
        }
    }
}
