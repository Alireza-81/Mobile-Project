// src/main/java/com/example/airline/SplashActivity.java
package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView airplaneImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        airplaneImage = findViewById(R.id.airplaneImage);

        Animation airplaneAnimation = AnimationUtils.loadAnimation(this, R.anim.airplane_animation);

        airplaneAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                airplaneImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, AdminMainMenuActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Not needed
            }
        });


        airplaneImage.startAnimation(airplaneAnimation);
    }
}
