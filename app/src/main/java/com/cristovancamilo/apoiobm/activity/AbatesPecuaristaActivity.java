package com.cristovancamilo.apoiobm.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.adapter.AdapterAbatesPecuarista;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.Base64Custom;
import com.cristovancamilo.apoiobm.helper.PreferenciasSistema;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.AbatesPecuarista;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AbatesPecuaristaActivity extends AppCompatActivity {

    private AdapterAbatesPecuarista adapterAbatesPecuarista;
    private RecyclerView recyclerViewAbatesPecuarista;
    private List<AbatesPecuarista> listaAbatesPecuarista = new ArrayList<>();
    private Retrofit retrofit;
    private SwipeRefreshLayout swipeContainer;
    private String cgc;
    private TextView nomeEmpresa;
    private PreferenciasSistema preferenciasSistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abates_pecuarista);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Meus Abates");

        preferenciasSistema = new PreferenciasSistema(getApplicationContext());

        nomeEmpresa =findViewById(R.id.textViewCabecalhoAbatesPecuarista);

        Bundle dados = getIntent().getExtras();
        cgc = dados.getString("cgc");

        //Configurando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

        //Configura SwipeRefreshLayout
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainerAbatesPecuarista);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recuperarAbatesPecuarista();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerViewAbatesPecuarista = findViewById(R.id.recycerViewAbatesPecuarista);

        recuperarAbatesPecuarista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sistema, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemSair :
                preferenciasSistema.salvarHorarioLogin("0", "", "");
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void recuperarAbatesPecuarista() {
        ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
        Call<List<AbatesPecuarista>> call = apoioBMService.recuperarAbatesPecuarista(Base64Custom.codificarBase64(cgc));

        listaAbatesPecuarista.clear();

        call.enqueue(new Callback<List<AbatesPecuarista>>() {
            @Override
            public void onResponse(Call<List<AbatesPecuarista>> call, Response<List<AbatesPecuarista>> response) {
                if(response.isSuccessful()) {
                    listaAbatesPecuarista = response.body();
                    configuraRecyclerView();
                    swipeContainer.setRefreshing(false);
                    nomeEmpresa.setText("Olá, " + listaAbatesPecuarista.get(0).getNome());
                }
            }

            @Override
            public void onFailure(Call<List<AbatesPecuarista>> call, Throwable t) {
                Toast.makeText(AbatesPecuaristaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void configuraRecyclerView() {
        //Configura Adapter
        adapterAbatesPecuarista = new AdapterAbatesPecuarista(listaAbatesPecuarista, this);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAbatesPecuarista.setLayoutManager(layoutManager);
        recyclerViewAbatesPecuarista.setHasFixedSize(true);
        recyclerViewAbatesPecuarista.setAdapter(adapterAbatesPecuarista);
    }
}