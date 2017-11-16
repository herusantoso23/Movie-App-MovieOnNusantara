package com.aragon.herusantoso.movieonnusantara.api;

import com.aragon.herusantoso.movieonnusantara.model.LinkResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by santoso on 11/17/17.
 */

public interface LinkService {

    @GET("get_link.php")
    Call<LinkResponse> view();
}
