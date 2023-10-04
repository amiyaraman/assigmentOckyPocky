package com.example.baloon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

//public class SplashScreen extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//    }
//}



public class SplashScreen extends AppCompatActivity {


    private int[] balloonColors = {R.drawable.blue_balloon, R.drawable.red_balloon};
    private int currentColorIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LinearLayout balloonContainer = findViewById(R.id.balloonContainer);

        for (int i = 0; i < 4; i++) {  // Create 4 rows of balloons
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setGravity(i % 2 == 0 ? Gravity.START : Gravity.END); // Align balloons left and right alternately
            balloonContainer.addView(rowLayout);

            for (int j = 0; j <= i; j++) { // Number of balloons in each row (increases with rows)
                ImageView balloon = new ImageView(this);
                balloon.setImageResource(balloonColors[currentColorIndex]);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(16, 16, 16, 16); // Set margin to create the desired pattern
                balloon.setLayoutParams(layoutParams);
                rowLayout.addView(balloon);

                animateBalloon(balloon);
                toggleBalloonColor();  // Toggle balloon color for the next balloon
            }
        }
    }

    private void animateBalloon(ImageView balloon) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f);

        translateAnimation.setDuration(5000); // 5 seconds duration for the animation
        translateAnimation.setFillAfter(true);

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation starts
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animation ends
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeats
            }
        });

        balloon.startAnimation(translateAnimation);
    }

    private void toggleBalloonColor() {
        currentColorIndex = (currentColorIndex + 1) % balloonColors.length;
    }
}







