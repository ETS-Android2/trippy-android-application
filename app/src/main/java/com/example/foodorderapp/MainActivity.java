package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variable for splash screen
    private static final int splashScreen = 6000;

    // Variables for animations
    Animation topAnim, bottomAnim, cartAnim, carrotAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_main);

        // Loading top and bottom animations in our splash activity
        topAnim = AnimationUtils.loadAnimation(this,R.anim.logo_top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.slogan_bottom_animation);
        cartAnim = AnimationUtils.loadAnimation(this,R.anim.cart_animation);
        carrotAnim = AnimationUtils.loadAnimation(this,R.anim.carrot_animation);


        // Setting animations to widgets
        TextView logo = findViewById(R.id.txtLogo);
        logo.setAnimation(topAnim);
        //ImageView cart = findViewById(R.id.cartView);
        //cart.setAnimation(cartAnim);
        //TextView slogan = findViewById(R.id.txtLogo);
        //logo.setAnimation(bottomAnim);
        //ImageView carrot = findViewById(R.id.carrotView);
        //logo.setAnimation(carrotAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity( intent );
                finish();
            }
        }, splashScreen);

    }
}