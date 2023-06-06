package br.ucs.servicescore.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ucs.servicescore.R;
import br.ucs.servicescore.entity.Place;
import br.ucs.servicescore.util.adapter.BusinessAdapter;
import br.ucs.servicescore.util.helper.DatabaseHelper;
import br.ucs.servicescore.util.helper.LocationHelper;
import br.ucs.servicescore.util.helper.YelpServiceHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView rvBusiness;
    private BusinessAdapter businessAdapter;
    private LocationHelper locationHelper;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        List<Place> places = getDatabaseHelper().buscarTodos();
        if (places == null || places.isEmpty()) {
            Log.i(TAG, "Nenhum dado encontrado no banco, realizando busca na API");
            getLocationHelper().pedirPermissao();
        } else {
            Log.i(TAG, "Total de resultados encontrados no banco: " + places.size());
            businessAdapter.getLstPlaces().addAll(places);
            getDatabaseHelper().addData(businessAdapter.getLstPlaces());
        }
    }

    private void initComponents() {
        businessAdapter = new BusinessAdapter(this);
        rvBusiness = findViewById(R.id.rvBusiness);
        rvBusiness.setLayoutManager(new LinearLayoutManager(this));
        rvBusiness.setAdapter(businessAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        locationHelper.handlePermissionResult(requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationHelper.stopLocationUpdates();
    }

    private LocationHelper getLocationHelper() {
        if (locationHelper == null) {
            locationHelper = new LocationHelper(this);
            locationHelper.setOnLocationChange(() -> {
                locationHelper.stopLocationUpdates();
                String cidade = locationHelper.getCity();

                // TODO: adicionar tratamento para caso a cidade não seja encontrada (pode acontecer caso a permissão da localização seja negada)

                Log.i(TAG, "Buscando dados da cidade: " + cidade);
                YelpServiceHelper.getInstance().getDataFromApi(cidade, () -> {
                    businessAdapter.getLstPlaces().addAll(YelpServiceHelper.getInstance().getPlaces());
                    getDatabaseHelper().addData(businessAdapter.getLstPlaces());
                    businessAdapter.notifyDataSetChanged();
                });
            });
        }
        return locationHelper;
    }

    private DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(this);
        }
        return databaseHelper;
    }

}
