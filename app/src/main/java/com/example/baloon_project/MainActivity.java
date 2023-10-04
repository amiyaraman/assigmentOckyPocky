package com.example.baloon_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView,imageView2,imageView3,imageView4;
    private TextView toastView;

     private String color;
    private Handler handler;
    private Random random;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        toastView = findViewById(R.id.toastView);
        handler = new Handler();
        random = new Random();
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);

        ConstraintLayout layout= findViewById(R.id.rootlayout);

        for (int i = 0; i < layout.getChildCount(); i++) {
            Log.println(Log.INFO, "onCreate: getchildcount",layout.getChildAt(i).getClass().toString());
            if (layout.getChildAt(i) instanceof TextView){
                continue;

            }

            final ImageView balloon = (ImageView) layout.getChildAt(i);

            updateBalloon(balloon);

            Animation move= AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate_anim);
            balloon.startAnimation(move);


        }

        for (int i = 0; i < layout.getChildCount(); i++) {
            Log.println(Log.INFO, "onCreate: getchildcount",layout.getChildAt(i).getClass().toString());
            if (layout.getChildAt(i) instanceof TextView){
                continue;

            }

            final ImageView balloon = (ImageView) layout.getChildAt(i);
            balloon.setOnClickListener(view -> {
                System.out.println(balloon.getTag());
                if  (balloon.getTag()==color){
                    burstBalloon(balloon);
                } else {

                    showToast("Wrong color! Try again.");


                }

            });


        }

    }


    private void burstBalloon(ImageView img) {
        vibrator.vibrate(200);


        showToast("Balloon Burst!");
        img.setImageResource(R.drawable.brust_image);
        handler.postDelayed(() -> {
        img.setVisibility(View.INVISIBLE);


        },500);







        // Delay to allow burst sound to finish
        handler.postDelayed(() -> {
            Animation move= AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate_anim);
            img.startAnimation(move);
            updateBalloon(img);
            img.setVisibility(View.VISIBLE);


        },1000);
    }

    @SuppressLint("SetTextI18n")
    private void updateBalloon(ImageView img1) {
        int number = (int)(Math.random()*10);
        if (number%2==0) {
            img1.setImageResource(R.drawable.blue_balloon);
            img1.setTag("blue");
            toastView.setText("Pop the Blue Balloon!");
            this.color="blue";

        } else {
            img1.setImageResource(R.drawable.red_balloon);
            toastView.setText("Pop the Red Balloon!");
            img1.setTag("red");
            this.color="red";

        }

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();}


}








