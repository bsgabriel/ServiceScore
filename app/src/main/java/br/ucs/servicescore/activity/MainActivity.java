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
import br.ucs.servicescore.util.helper.YelpServiceHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView rvBusiness;
    private BusinessAdapter businessAdapter;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        YelpServiceHelper.getInstance().getDataFromApi(() -> {
            businessAdapter.getLstBusinesses().addAll(YelpServiceHelper.getInstance().getReturnedData());
            businessAdapter.notifyDataSetChanged();
        });
    }

    private void initComponents(){
        businessAdapter = new BusinessAdapter(this);
        rvBusiness = findViewById(R.id.rvBusiness);
        rvBusiness.setLayoutManager(new LinearLayoutManager(this));
        rvBusiness.setAdapter(businessAdapter);
    }


}
