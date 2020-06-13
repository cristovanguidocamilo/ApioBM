package com.cristovancamilo.apoiobm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.cristovancamilo.apoiobm.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private Animation animation;
    private String exibeSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        imageView.startAnimation(animation);

        exibeSplash = "S";
        mostrarSplash();


    }

    public void mostrarSplash() {
        if(exibeSplash == "S") {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
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