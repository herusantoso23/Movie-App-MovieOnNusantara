package com.aragon.herusantoso.movieonnusantara;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aragon.herusantoso.movieonnusantara.adapter.MovieAdapter;
import com.aragon.herusantoso.movieonnusantara.api.ApiClient;
import com.aragon.herusantoso.movieonnusantara.api.Service;
import com.aragon.herusantoso.movieonnusantara.model.Movie;
import com.aragon.herusantoso.movieonnusantara.model.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by santoso on 11/17/17.
 */

public class MainTopRatedFragment extends Fragment {
    private View myView;

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movie> movieList;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    public static final String LOG_TAG = MovieAdapter.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_main, container, false);

        initViews();

        swipeContainer = (SwipeRefreshLayout) myView.findViewById(R.id.main_content);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(getActivity().getApplicationContext(),
                        "Movie Refresh",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return myView;
    }

    private void initViews(){
        pd = new ProgressDialog(getActivity());
        pd.setMessage("fetching movies...");
        pd.setCancelable(false);
        pd.show();

        recyclerView =(RecyclerView) myView.findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        adapter = new MovieAdapter(getActivity(), movieList);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJSON1();
    }


    private void loadJSON1(){
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(getActivity().getApplicationContext(),
                        "Please Obtain API Key firstly from themoviedb.org",
                        Toast.LENGTH_SHORT).show();
                pd.dismiss();
                return;
            }

            ApiClient client = new ApiClient();
            Service apiService = ApiClient.getClient().create(Service.class);
            Call<MoviesResponse> call = apiService.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    List<Movie> movies = response.body().getResults();
                    recyclerView.setAdapter(new MovieAdapter(getActivity().getApplicationContext(), movies));
                    recyclerView.smoothScrollToPosition(0);

                    if(swipeContainer.isRefreshing()){
                        swipeContainer.setRefreshing(false);

                    }

                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Error Fetching Data !",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
