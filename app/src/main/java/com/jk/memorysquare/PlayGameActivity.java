package com.jk.memorysquare;

import android.os.Handler;
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

    void animateButton(View view){
        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
        view.startAnimation(ranim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        myHandler = new Handler(this);

        final TextView overlayText = (TextView) findViewById(R.id.overlayText);

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1000);

        in.setAnimationListener(new Animation.AnimationListener() {

            TextView overlayText = (TextView) findViewById(R.id.overlayText);

            @Override
            public void onAnimationStart(Animation animation) {
                overlayText.setText("Level 1");
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //Not used for now
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //Animate the moves for current level after the out animation is done
                        MyUIHandlerThread.triggerAnimations(10);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //Not used for now
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
        Log.d("PlayGameActivity", "result:" + result);

        switch (result) {
            case 1:
                animateButton(findViewById(R.id.redButton));
                break;
            case 2:
                animateButton(findViewById(R.id.yellowButton));
                break;
            case 3:
                animateButton(findViewById(R.id.blueButton));
                break;
            case 4:
                animateButton(findViewById(R.id.greenButton));
                break;
            default:
                break;
        }

        return false;
    }

}

