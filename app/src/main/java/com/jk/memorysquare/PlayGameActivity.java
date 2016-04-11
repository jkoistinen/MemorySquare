package com.jk.memorysquare;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //set onclicklistener for imagebuttons
        ImageButton redButton = (ImageButton) findViewById(R.id.redButton);
        ImageButton yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        ImageButton blueButton = (ImageButton) findViewById(R.id.blueButton);
        ImageButton greenButton = (ImageButton) findViewById(R.id.greenButton);

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Red", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                view.startAnimation(ranim);
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Yellow", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                view.startAnimation(ranim);
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Blue", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                view.startAnimation(ranim);
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Green", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                view.startAnimation(ranim);
            }
        });

    }
}
