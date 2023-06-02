package br.ucs.servicescore.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpService {

    @GET("businesses/search")
    Call<?> searchRestaurants(@Query("term") String searchTerm, @Query("location") String location);
}
