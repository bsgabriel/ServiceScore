package br.ucs.servicescore.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ucs.servicescore.R;
import br.ucs.servicescore.entity.Business;
import br.ucs.servicescore.entity.YelpSearchResponse;
import br.ucs.servicescore.service.YelpService;
import br.ucs.servicescore.util.GlobalKeys;
import br.ucs.servicescore.util.adapter.BusinessAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView rvBusiness;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Pegar a cidade com base na localização do celular
        // TODO: Recriar as entidades utilizando apenas os campos necessários
        // TODO: Verificar se existe dados no banco. Caso não tenha, carrega da API e salva

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        
        List<Business> lstBusiness = new ArrayList<>();
        BusinessAdapter businessAdapter = new BusinessAdapter(this, lstBusiness);
        rvBusiness = findViewById(R.id.rvBusiness);
        rvBusiness.setLayoutManager(new LinearLayoutManager(this));
        rvBusiness.setAdapter(businessAdapter);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(GlobalKeys.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        YelpService service = retrofit.create(YelpService.class);
        service.searchBusiness("Bearer " + GlobalKeys.API_KEY,"Caxias do Sul").enqueue(new Callback() {
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i(TAG, "onFailure " + t.toString());

            }

            @Override
            public void onResponse(Call call, Response response) {

                if(response.body() == null){
                    Log.w(TAG, "Não houve retorno da API.");
                    return;
                }

                Log.i(TAG, "body instanceof YelpSearchResponse: " +  (response.body() instanceof YelpSearchResponse));
                YelpSearchResponse retorno = (YelpSearchResponse) response.body();
                lstBusiness.addAll(retorno.getBusinesses());
                Log.i(TAG, "retorno.size: " + lstBusiness.size());
                businessAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * Repsonsável pela inicialização de componentes
     */
    private void initComponents () {
        ;


    }
}
