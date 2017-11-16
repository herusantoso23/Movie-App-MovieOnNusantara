package com.aragon.herusantoso.movieonnusantara.api;

import com.aragon.herusantoso.movieonnusantara.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by santoso on 11/14/17.
 */

public interface Service  {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(
            @Query("api_key") String apiKey);


    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(
            @Query("api_key") String apiKey);


}
