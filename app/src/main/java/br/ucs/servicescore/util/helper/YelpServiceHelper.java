package br.ucs.servicescore.util.helper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ucs.servicescore.entity.yelpresponse.Business;
import br.ucs.servicescore.entity.Place;
import br.ucs.servicescore.entity.yelpresponse.YelpSearchResponse;
import br.ucs.servicescore.service.YelpService;
import br.ucs.servicescore.util.GlobalKeys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpServiceHelper {
    private final static String TAG = "YelpServiceHelper";
    private static YelpServiceHelper instance;

    private YelpService service;
    private List<Place> places;

    private YelpServiceHelper() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GlobalKeys.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(YelpService.class);
    }

    public static YelpServiceHelper getInstance() {
        if (instance == null)
            instance = new YelpServiceHelper();

        return instance;
    }

    public List<Place> getPlaces() {
        if (places == null)
            places = new ArrayList<>();

        return places;
    }

    public void getDataFromApi(String cidade, Runnable afterCall) {
        service.searchBusiness("Bearer " + GlobalKeys.API_KEY, cidade).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i(TAG, "onFailure " + t.toString());

            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() == null) {
                    Log.w(TAG, "Não houve retorno da API.");
                    return;
                }

                getPlaces().clear();
                YelpSearchResponse retorno = (YelpSearchResponse) response.body();
                for(Business business : retorno.getBusinesses()){
                    Place place = new Place();
                    place.setId(business.getId());
                    place.setNome(business.getName());
                    place.setAvaliacao(business.getRating());
                    place.setCategoria(business.getCategories().get(0).getTitle());
                    place.setEndereco(business.getLocation().getAddress1());
                    place.setNumAvaliacoes(business.getReviewCount());
                    place.setUrlImage(business.getImageUrl());
                    getPlaces().add(place);
                }

                if (afterCall != null)
                    afterCall.run();

            }
        });
    }

}
