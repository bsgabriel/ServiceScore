package br.ucs.servicescore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import br.ucs.servicescore.entity.YelpSearchResponse;
import br.ucs.servicescore.service.YelpService;
import br.ucs.servicescore.util.GlobalKeys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(GlobalKeys.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        YelpService service = retrofit.create(YelpService.class);
        service.searchRestaurants("Bearer " + GlobalKeys.API_KEY, "Talharim", "Caxias do Sul").enqueue(new Callback() {
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i(TAG, "onFailure " + t.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.i(TAG, "onResponse " + response.toString());
                Log.i(TAG, "---> response.body() instanceof YelpSearchResponse: " + (response.body() instanceof YelpSearchResponse));
            }
        });
    }
} 