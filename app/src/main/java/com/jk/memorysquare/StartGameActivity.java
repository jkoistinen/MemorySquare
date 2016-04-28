package com.jk.memorysquare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    public static final String USERPREFERENCEFILE = "MyPrefsFile";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"Clear highscore");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        Integer restoredLevel = 1;
        SharedPreferences.Editor editor = getSharedPreferences(USERPREFERENCEFILE, MODE_PRIVATE).edit();
        editor.putInt("level", restoredLevel);
        editor.commit();

        return super.onOptionsItemSelected(item);
    }

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
