package com.cristovancamilo.apoiobm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.model.EstoqueBloqueado;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void abrirEstoqueBloqueado(View view) {
        Intent intent = new Intent(MainActivity.this, EstoqueBloqueadoActivity.class);
        startActivity(intent);
    }

    public void abrirCamaras(View view) {
        Intent intent = new Intent(MainActivity.this, CamarasActivity.class);
        startActivity(intent);
    }

}