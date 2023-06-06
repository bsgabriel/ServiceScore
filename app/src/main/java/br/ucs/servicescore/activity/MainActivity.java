package br.ucs.servicescore.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ucs.servicescore.R;
import br.ucs.servicescore.entity.Place;
import br.ucs.servicescore.util.MessageFactory;
import br.ucs.servicescore.util.adapter.BusinessAdapter;
import br.ucs.servicescore.util.helper.DatabaseHelper;
import br.ucs.servicescore.util.helper.LocationHelper;
import br.ucs.servicescore.util.helper.YelpServiceHelper;
import br.ucs.servicescore.util.interfaces.MessageCallback;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView rvBusiness;
    private Button btnRefresh;
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
            businessAdapter.notifyDataSetChanged();
        }
    }

    private void initComponents() {
        businessAdapter = new BusinessAdapter(this);
        rvBusiness = findViewById(R.id.rvBusiness);
        rvBusiness.setLayoutManager(new LinearLayoutManager(this));
        rvBusiness.setAdapter(businessAdapter);

        btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener((event) -> {
            businessAdapter.getLstPlaces().clear();
            businessAdapter.notifyDataSetChanged();
            getLocationHelper().pedirPermissao();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        getLocationHelper().handlePermissionResult(requestCode, grantResults);
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
                buscarCidade(cidade);
            });


            locationHelper.setOnPermissionDenied(() -> {
                MessageFactory.showInputDialog(MainActivity.this, "Digite a cidade", new MessageCallback() {
                    @Override
                    public void onConfirm(String userInput) {
                        if (userInput == null || userInput.trim().isEmpty()) {
                            Log.w(TAG, "Usuário não digitou  nenuma cidade");
                            return;
                        }
                        buscarCidade(userInput);
                    }

                    @Override
                    public void onCancel() {
                        Log.w(TAG, "Usuáiro não informou cidade, não haverá busca");
                        Toast.makeText(MainActivity.this, "É necessário informar a cidade!", Toast.LENGTH_LONG);
                    }
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

    private void buscarCidade(String cidade) {
        Log.i(TAG, "Buscando dados da cidade: " + cidade);

        YelpServiceHelper.getInstance().getDataFromApi(cidade, () -> {
            businessAdapter.getLstPlaces().addAll(YelpServiceHelper.getInstance().getPlaces());
            getDatabaseHelper().deleteAll();
            getDatabaseHelper().addData(businessAdapter.getLstPlaces());
            businessAdapter.notifyDataSetChanged();
        });
    }
}
