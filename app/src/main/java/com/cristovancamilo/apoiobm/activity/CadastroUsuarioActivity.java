package com.cristovancamilo.apoiobm.activity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.api.ApoioBMService;
import com.cristovancamilo.apoiobm.helper.Base64Custom;
import com.cristovancamilo.apoiobm.helper.RetrofitConfig;
import com.cristovancamilo.apoiobm.model.ValidaUsuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextInputEditText textCPFCNPJ, textUsuario, textSenha, textConfSenha;
    private Button btnCadastrar, btnCancelar;
    private Retrofit retrofit;
    private List<ValidaUsuario> listaValidaUsuario = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        textCPFCNPJ = findViewById(R.id.textCadUsuCPFCNPJ);
        textUsuario = findViewById(R.id.textCadUsuUsuario);
        textSenha = findViewById(R.id.textCadUsuSenha);
        textConfSenha = findViewById(R.id.textCadUsuConfSenha);
        btnCadastrar = findViewById(R.id.btnCadUsuCadastrar);
        btnCancelar = findViewById(R.id.btnCadUsuCancelar);

        //Configurando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

    }

    public void cadastarUsuario(View view) {
        if(validarEmail(textUsuario.getText().toString())) {
            if (textSenha.getText().toString().equals(textConfSenha.getText().toString())) {
                ApoioBMService apoioBMService = retrofit.create(ApoioBMService.class);
                Call<List<ValidaUsuario>> call = apoioBMService.cadasatraUsuario(Base64Custom.codificarBase64(textUsuario.getText().toString()), Base64Custom.codificarBase64(textSenha.getText().toString()), Base64Custom.codificarBase64(textCPFCNPJ.getText().toString()));

                call.enqueue(new Callback<List<ValidaUsuario>>() {
                    @Override
                    public void onResponse(Call<List<ValidaUsuario>> call, Response<List<ValidaUsuario>> response) {
                        String mensagem = "";
                        if (response.isSuccessful()) {
                            listaValidaUsuario.clear();
                            listaValidaUsuario = response.body();
                            if (listaValidaUsuario.get(0).getResult().equals("RET000")) {
                                mensagem = "Usuário Cadastrado com Sucesso!";
                                finish();
                            } else if (listaValidaUsuario.get(0).getResult().equals("RET001")) {
                                mensagem = "CPF ou CNPJ Inválido!";
                            } else if (listaValidaUsuario.get(0).getResult().equals("RET002")) {
                                mensagem = "Usuário já Cadastrado!";
                            } else if (listaValidaUsuario.get(0).getResult().equals("RET003")) {
                                mensagem = "Erro ao gravar no Banco de Dados!";
                            }
                            Toast.makeText(CadastroUsuarioActivity.this, mensagem, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ValidaUsuario>> call, Throwable t) {
                        Toast.makeText(CadastroUsuarioActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(CadastroUsuarioActivity.this, "Senha Não Confere! Digite Novamente!", Toast.LENGTH_LONG).show();
                textSenha.setText("");
                textConfSenha.setText("");
            }
        } else {
            Toast.makeText(CadastroUsuarioActivity.this, "(" + textUsuario.getText().toString() + ") Não é um e-mail Válido!", Toast.LENGTH_LONG).show();
            textUsuario.setText("");
        }
    }

    public boolean validarEmail (String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void cancelar(View view) {
        finish();
    }

}