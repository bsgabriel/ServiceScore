package br.ucs.servicescore.util.helper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ucs.servicescore.entity.Business;
import br.ucs.servicescore.entity.YelpSearchResponse;
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
    private List<Business> returnedData;

    private YelpServiceHelper() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GlobalKeys.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(YelpService.class);
    }

    public static YelpServiceHelper getInstance() {
        if (instance == null)
            instance = new YelpServiceHelper();

        return instance;
    }

    public List<Business> getReturnedData() {
        if (returnedData == null)
            returnedData = new ArrayList<>();

        return returnedData;
    }

    public void getDataFromApi(Runnable afterCall) {

        service.searchBusiness("Bearer " + GlobalKeys.API_KEY, "Caxias do Sul").enqueue(new Callback() {
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

                getReturnedData().clear();
                YelpSearchResponse retorno = (YelpSearchResponse) response.body();
                getReturnedData().addAll(retorno.getBusinesses());

                if (afterCall != null)
                    afterCall.run();
            }
        });
    }

}
