package com.cristovancamilo.apoiobm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.RecyclerItemClickListener;
import com.cristovancamilo.apoiobm.adapter.AdapterEscalaAbate;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.EscalaAbate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EscalaAbateActivity extends AppCompatActivity {

    private AdapterEscalaAbate adapterEscalaAbate;
    private RecyclerView recyclerViewEscalaAbate;
    private List<EscalaAbate> listEscalaAbate = new ArrayList<>();
    private Retrofit retrofit;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escala_abate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Escala de Abate");

        //Configurando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

        //Configura SwipeRefreshLayout
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swapContainerEscalaAbate);
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

        recyclerViewEscalaAbate = findViewById(R.id.recyclerViewEscalaAbate);

        recuperarCamaras();

    }

    public void recuperarCamaras() {
        ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
        Call<List<EscalaAbate>> call = apoioBMService.recuperarEscalaAbate();

        call.enqueue(new Callback<List<EscalaAbate>>() {
            @Override
            public void onResponse(Call<List<EscalaAbate>> call, Response<List<EscalaAbate>> response) {
                if(response.isSuccessful()) {
                    listEscalaAbate.clear();
                    listEscalaAbate = response.body();
                    configuraRecyclerView();
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<EscalaAbate>> call, Throwable t) {
                Toast.makeText(EscalaAbateActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void configuraRecyclerView() {
        //Configura Adapter
        adapterEscalaAbate = new AdapterEscalaAbate(listEscalaAbate, this);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewEscalaAbate.setLayoutManager(layoutManager);
        recyclerViewEscalaAbate.setHasFixedSize(true);
        recyclerViewEscalaAbate.setAdapter(adapterEscalaAbate);

        recyclerViewEscalaAbate.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerViewEscalaAbate, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                EscalaAbate escalaAbate = listEscalaAbate.get(position);
                abrirQuantidadesLote(escalaAbate.getLote().toString());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

    public void abrirQuantidadesLote(String num_lote) {
        Intent intent = new Intent(EscalaAbateActivity.this, QuantidadesLoteActivity.class);
        intent.putExtra("num_lote", num_lote);
        startActivity(intent);

    }
}