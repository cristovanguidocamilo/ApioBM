package com.cristovancamilo.apoiobm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.adapter.AdapterEstoqueBloqueado;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.EstoqueBloqueado;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstoqueBloqueadoActivity extends AppCompatActivity {

    private AdapterEstoqueBloqueado adapterEstoqueBloqueado;
    private RecyclerView recyclerView;
    private List<EstoqueBloqueado> listaEstoqueBloqueado = new ArrayList<>();
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque_bloqueado);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Estoque Bloqueado");

        //Configurando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

        recyclerView = findViewById(R.id.recyclerViewEstoqueBloqueado);

        recuperarEstoqueBloqueado();

    }

    public void recuperarEstoqueBloqueado() {
        ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
        Call<List<EstoqueBloqueado>> call = apoioBMService.recuperarEstoqueBloqueado();

        listaEstoqueBloqueado.clear();

        call.enqueue(new Callback<List<EstoqueBloqueado>>() {
            @Override
            public void onResponse(Call<List<EstoqueBloqueado>> call, Response<List<EstoqueBloqueado>> response) {
                if(response.isSuccessful()) {
                    listaEstoqueBloqueado = response.body();

                    configuraRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<EstoqueBloqueado>> call, Throwable t) {
                Toast.makeText(EstoqueBloqueadoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void configuraRecyclerView() {
        //Configura Adapter
        adapterEstoqueBloqueado = new AdapterEstoqueBloqueado(listaEstoqueBloqueado);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterEstoqueBloqueado);
    }

}