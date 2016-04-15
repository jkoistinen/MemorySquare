package com.jk.memorysquare;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PlayGameActivity extends AppCompatActivity {

    ImageButton redButton;
    ImageButton yellowButton;
    ImageButton blueButton;
    ImageButton greenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //set animation on textview
        final TextView overlayText = (TextView) findViewById(R.id.textView);

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(3000);

        in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //Not used for now
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                overlayText.setText("Level 1");
                overlayText.startAnimation(out);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //Not used for now
            }
        });

        overlayText.startAnimation(in);

        //END set animation on textview

        //set onclicklistener for imagebuttons
        redButton = (ImageButton) findViewById(R.id.redButton);
        yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        blueButton = (ImageButton) findViewById(R.id.blueButton);
        greenButton = (ImageButton) findViewById(R.id.greenButton);

        //animate a few imagebuttons once, just for show....
        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.flipimagebutton);
        redButton.startAnimation(ranim);
        greenButton.startAnimation(ranim);

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                view.startAnimation(ranim);
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                view.startAnimation(ranim);
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                view.startAnimation(ranim);
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
                view.startAnimation(ranim);
            }
        });

    }
}
