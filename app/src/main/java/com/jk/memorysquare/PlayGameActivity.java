package com.jk.memorysquare;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
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

public class PlayGameActivity extends AppCompatActivity implements Handler.Callback{

        ImageButton redButton;
        ImageButton yellowButton;
        ImageButton blueButton;
        ImageButton greenButton;

        private final static String MyName = "MyCustomUIHandlerThread";
        private MyUIHandlerThread myCustomHandlerThread;
        private Handler myHandler;

    public void runCurrentLevelAnimations() {

        //read in previous animation sequence from some list
        MyUIHandlerThread.querySomething(100, 150);

    }

    void animateButton(View view){
        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
        view.startAnimation(ranim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        myHandler = new Handler(this);

        //set animation on overlayText
        final TextView overlayText = (TextView) findViewById(R.id.overlayText);

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(3000);

        in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                overlayText.setText("Level 1");
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //Animate the moves for current level after the out animation is done
                        runCurrentLevelAnimations();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

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

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton(view);
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton(view);
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton(view);
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton(view);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        myCustomHandlerThread = new MyUIHandlerThread(MyName);
        myCustomHandlerThread.setCallback(myHandler);
        myCustomHandlerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        myCustomHandlerThread.setCallback(null);
        myCustomHandlerThread.quit();
        myCustomHandlerThread = null;
    }

    @Override
    public boolean handleMessage(Message arg0) {
        int result = (Integer)arg0.obj;
        Log.d("PlayGameActivity", "result:" + result );
        animateButton(findViewById(R.id.greenButton));

        return false;
    }

}

