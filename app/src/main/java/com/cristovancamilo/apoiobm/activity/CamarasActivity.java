package com.cristovancamilo.apoiobm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.adapter.AdapterCamaras;
import com.cristovancamilo.apoiobm.adapter.AdapterEstoqueBloqueado;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.Camaras;
import com.cristovancamilo.apoiobm.model.EstoqueBloqueado;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CamarasActivity extends AppCompatActivity {

    private AdapterCamaras adapterCamaras;
    private RecyclerView recyclerViewCamaras;
    private List<Camaras> listaCamaras = new ArrayList<>();
    private Retrofit retrofit;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camaras);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Câmaras Abate");

        //Configurando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

        //Configura SwipeRefreshLayout
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainerCamaras);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recuperarCamaras();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerViewCamaras = findViewById(R.id.recyclerViewCamaras);

        recuperarCamaras();
    }

    public void recuperarCamaras() {
        ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
        Call<List<Camaras>> call = apoioBMService.recuperarCamaras();

        listaCamaras.clear();

        call.enqueue(new Callback<List<Camaras>>() {
            @Override
            public void onResponse(Call<List<Camaras>> call, Response<List<Camaras>> response) {
                if(response.isSuccessful()) {
                    listaCamaras = response.body();
                    configuraRecyclerView();
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<Camaras>> call, Throwable t) {
                Toast.makeText(CamarasActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void configuraRecyclerView() {
        //Configura Adapter
        adapterCamaras = new AdapterCamaras(listaCamaras);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCamaras.setLayoutManager(layoutManager);
        recyclerViewCamaras.setHasFixedSize(true);
        recyclerViewCamaras.setAdapter(adapterCamaras);
    }

}