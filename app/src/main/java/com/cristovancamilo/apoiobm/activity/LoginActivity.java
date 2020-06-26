package com.cristovancamilo.apoiobm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.ValidaUsuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private List<ValidaUsuario> listaValidaUsuario = new ArrayList<>();
    TextInputEditText textUsuario, textSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUsuario = findViewById(R.id.textUsuario);
        textSenha = findViewById(R.id.textSenha);

        //Configurando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

    }

    public void abrirSplashScreen() {
        Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    public void validarUsuario(View view) {

        ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
        Call<List<ValidaUsuario>> call = apoioBMService.validarUsuario(textUsuario.getText().toString(), textSenha.getText().toString());

        call.enqueue(new Callback<List<ValidaUsuario>>() {
            @Override
            public void onResponse(Call<List<ValidaUsuario>> call, Response<List<ValidaUsuario>> response) {
                if(response.isSuccessful()) {
                    listaValidaUsuario.clear();
                    listaValidaUsuario = response.body();
                    if(listaValidaUsuario.get(0).getResult().equals("Login Permitido")) {
                        abrirSplashScreen();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ValidaUsuario>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}