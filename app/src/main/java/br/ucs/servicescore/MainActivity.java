package br.ucs.servicescore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.ucs.servicescore.service.YelpService;
import br.ucs.servicescore.util.GlobalKeys;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(GlobalKeys.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        YelpService service = retrofit.create(YelpService.class);
        service.searchRestaurants("Talharim", "Caxias do sul").enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
} 