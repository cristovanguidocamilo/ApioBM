package com.cristovancamilo.apoiobm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cristovancamilo.apoiobm.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private Animation animation;
    private String exibeSplash;
    private String cgc, tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        imageView.startAnimation(animation);

        Bundle dados = getIntent().getExtras();
        cgc = dados.getString("cgc");
        tipo = dados.getString("tipo");

        exibeSplash = "S";
        mostrarSplash();


    }

    public void mostrarSplash() {
        if(exibeSplash == "S") {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(tipo.equals("2") || tipo == "2") {
                        Intent intent = new Intent(SplashActivity.this, AbatesPecuaristaActivity.class);
                        intent.putExtra("cgc", cgc);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 3000);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        exibeSplash = "N";
    }
}