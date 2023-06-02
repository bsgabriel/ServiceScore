package br.ucs.servicescore.service;

import br.ucs.servicescore.entity.YelpSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface YelpService {

    @GET("businesses/search")
    Call<YelpSearchResponse> searchRestaurants(@Header("Authorization") String token,//
                                               @Query("term") String searchTerm, //
                                               @Query("location") String location);
}
