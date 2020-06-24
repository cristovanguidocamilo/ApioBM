package com.cristovancamilo.apoiobm.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.adapter.AdapterQuantidadesLote;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.QuantidadesLote;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuantidadesLoteActivity extends AppCompatActivity {

    private AdapterQuantidadesLote adapterQuantidadesLote;
    private RecyclerView recyclerView;
    private List<QuantidadesLote> listaQuantidadesLote = new ArrayList<>();
    private Retrofit retrofit;
    private SwipeRefreshLayout swipeContainer;
    private String numLote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantidades_lote);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quantidades Por Lote");

        Bundle dados = getIntent().getExtras();
        numLote = dados.getString("num_lote");
        if(numLote.isEmpty() || numLote.equals("")) {
            numLote = "00";
        }

        //Configurando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

        //Configura SwipeRefreshLayout
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainerQuantidadesLote);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recuperarQuantidadesLote(numLote);
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerView = findViewById(R.id.recyclerViewQuantidadesLote);

        recuperarQuantidadesLote(numLote);
    }

    public void recuperarQuantidadesLote(String num_lote) {
        ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
        Call<List<QuantidadesLote>> call = apoioBMService.recuperarQuantidadesLote(num_lote);

        call.enqueue(new Callback<List<QuantidadesLote>>() {
            @Override
            public void onResponse(Call<List<QuantidadesLote>> call, Response<List<QuantidadesLote>> response) {
                if(response.isSuccessful()) {
                    listaQuantidadesLote.clear();
                    listaQuantidadesLote = response.body();
                    configuraRecyclerView();
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<QuantidadesLote>> call, Throwable t) {
                Toast.makeText(QuantidadesLoteActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void configuraRecyclerView() {
        //Configura Adapter
        adapterQuantidadesLote = new AdapterQuantidadesLote(listaQuantidadesLote, this);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterQuantidadesLote);
    }
}