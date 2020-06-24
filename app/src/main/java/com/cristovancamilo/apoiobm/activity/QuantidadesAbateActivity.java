package com.cristovancamilo.apoiobm.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.adapter.AdapterQuantidadesAbate;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.QuantidadesAbate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuantidadesAbateActivity extends AppCompatActivity {

    private AdapterQuantidadesAbate adapterQuantidadesAbate;
    private RecyclerView recyclerView;
    private List<QuantidadesAbate> listaQuantidadesAbate = new ArrayList<>();
    private Retrofit retrofit;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantidades_abate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Resumo do Abate");

        //Configurando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

        //Configura SwipeRefreshLayout
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainerQuantidadesAbate);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recuperarQuantidadesAbate();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerView = findViewById(R.id.recyclerViewQuantidadesAbate);

        recuperarQuantidadesAbate();
    }

    public void recuperarQuantidadesAbate() {
        ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
        Call<List<QuantidadesAbate>> call = apoioBMService.recuperarQuantidadesAbate();

        call.enqueue(new Callback<List<QuantidadesAbate>>() {
            @Override
            public void onResponse(Call<List<QuantidadesAbate>> call, Response<List<QuantidadesAbate>> response) {
                if(response.isSuccessful()) {
                    listaQuantidadesAbate.clear();
                    listaQuantidadesAbate = response.body();
                    configuraRecyclerView();
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<QuantidadesAbate>> call, Throwable t) {
                Toast.makeText(QuantidadesAbateActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void configuraRecyclerView() {
        //Configura Adapter
        adapterQuantidadesAbate = new AdapterQuantidadesAbate(listaQuantidadesAbate, this);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterQuantidadesAbate);
    }
}