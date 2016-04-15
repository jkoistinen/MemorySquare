package com.jk.memorysquare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;

public class StartGameActivity extends AppCompatActivity {

    Button startButton;

    ImageButton redButton;
    ImageButton greenButton;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        startButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Start game button launches PlayGameActivity
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                redButton = (ImageButton) findViewById(R.id.redButton);
                greenButton = (ImageButton) findViewById(R.id.greenButton);

                //animate a few imagebuttons once, just for show....
                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                redButton.startAnimation(ranim);
                greenButton.startAnimation(ranim);

                ranim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //Fade out the Start Game button
                        Animation out = new AlphaAnimation(1.0f, 0.0f);
                        out.setDuration(3000);

                        startButton.startAnimation(out);
                        startButton.setVisibility(View.INVISIBLE);

                        //Change activity after the fade is done (onAnimationEnd)
                        out.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                //
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Intent i = new Intent(getApplicationContext(), PlayGameActivity.class);
                                startActivity(i);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                                //
                            }
                        });

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //
                    }
                });


            }
        });



    }

}
