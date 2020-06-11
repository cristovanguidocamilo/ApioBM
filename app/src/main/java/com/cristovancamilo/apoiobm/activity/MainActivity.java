package com.cristovancamilo.apoiobm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.Acompanha;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private TextView textoTotalAbate, textoAbatidos, textoRestam;
    private ProgressBar pbQuantidadeAbate;
    private List<Acompanha> listaAcompanhaAbate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = RetrofitConfig.getRetrofit();
        textoAbatidos = findViewById(R.id.textViewAbatidos);
        textoRestam = findViewById(R.id.textViewRestam);
        textoTotalAbate = findViewById(R.id.textViewTotalAbate);
        pbQuantidadeAbate = findViewById(R.id.pbProgressoAbate);

    }



    public void abrirEstoqueBloqueado(View view) {
        Intent intent = new Intent(MainActivity.this, EstoqueBloqueadoActivity.class);
        startActivity(intent);
    }

    public void abrirCamaras(View view) {
        Intent intent = new Intent(MainActivity.this, CamarasActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int delay = 5000;
        int interval = 10000;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                atualizarQuantidadesAbate();
            }
        }, delay, interval);
    }

    public void atualizarQuantidadesAbate() {
        ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
        Call<List<Acompanha>> call = apoioBMService.recuperarAcompanhaAbate();

        listaAcompanhaAbate.clear();

       call.enqueue(new Callback<List<Acompanha>>() {
            @Override
            public void onResponse(Call<List<Acompanha>> call, Response<List<Acompanha>> response) {
                if(response.isSuccessful()) {
                    listaAcompanhaAbate = response.body();

                    textoTotalAbate.setText("Total: " + listaAcompanhaAbate.get(0).getTotal().toString());
                    textoAbatidos.setText("Abatidos: " + listaAcompanhaAbate.get(0).getAbatidos().toString());
                    textoRestam.setText("Restam: " + listaAcompanhaAbate.get(0).getRestam().toString());

                    pbQuantidadeAbate.setMax(Integer.parseInt(listaAcompanhaAbate.get(0).getTotal().toString()));
                    pbQuantidadeAbate.setProgress(Integer.parseInt(listaAcompanhaAbate.get(0).getAbatidos().toString()));

                }
            }

            @Override
            public void onFailure(Call<List<Acompanha>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}