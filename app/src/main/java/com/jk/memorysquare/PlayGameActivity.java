package com.jk.memorysquare;

import android.content.SharedPreferences;
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

import java.util.ArrayList;

public class PlayGameActivity extends AppCompatActivity implements Handler.Callback{

        ImageButton redButton;
        ImageButton yellowButton;
        ImageButton blueButton;
        ImageButton greenButton;

        private final static String MyName = "MyCustomUIHandlerThread";
        private MyUIHandlerThread myCustomHandlerThread;
        private Handler myHandler;

        private static String TAG = "PlayGameActivity";

        public static String levelSequence = "123312341234";

        private static ArrayList<Integer> guessedSequence = new ArrayList<>();

        private static Integer guessCounter = 0;

        private static Integer totalCorrectGuesses = 0;

        public static final String USERPREFERENCEFILE = "MyPrefsFile";

    void animateButton(View view){
        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.flipimagebutton);
        view.startAnimation(ranim);
    }

    int getCurrentLevel(){

        SharedPreferences prefs = getSharedPreferences(USERPREFERENCEFILE, MODE_PRIVATE);
        Integer restoredLevel = prefs.getInt("level", 1);

        return restoredLevel;
    }

    void setNextLevel(){

        SharedPreferences prefs = getSharedPreferences(USERPREFERENCEFILE, MODE_PRIVATE);
        Integer restoredLevel = prefs.getInt("level", 1);

        restoredLevel++;

        SharedPreferences.Editor editor = getSharedPreferences(USERPREFERENCEFILE, MODE_PRIVATE).edit();
        editor.putInt("level", restoredLevel);
        editor.commit();
        Log.d(TAG,  "new level stored!");

    }

    void saveGuess(int id){
        guessedSequence.add(id);
        guessCounter++;

    }

    void verifyGuess(){
        int thisGuess = guessedSequence.get(guessCounter-1);
        int correctGuess = Character.getNumericValue(levelSequence.charAt(guessCounter-1));

        if(thisGuess == correctGuess){
            Log.d(TAG, "Correct!");
            totalCorrectGuesses++;

            if(totalCorrectGuesses == getCurrentLevel()){
                Log.d(TAG, "Level won!");
                setNextLevel();
                nextLevel();
            }

        } else {
            Log.d(TAG, "Incorrect!");
            restartLevel();
        }

    }

    void nextLevel(){
        changeoverlayText("Great! Next level...");
    }

    void restartLevel(){
        changeoverlayText("Oopsie...");
    }

    void changeTurnoverlayText(){

        TextView overlayText = (TextView) findViewById(R.id.overlayText);
        overlayText.setText("Your turn!");

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;
        overlayText.startAnimation(fadeIn);
        overlayText.startAnimation(fadeOut);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(1000 + fadeIn.getStartOffset());

    }

    void changeoverlayText(String text){

        TextView overlayText = (TextView) findViewById(R.id.overlayText);
        overlayText.setText(text);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                loadGame();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        overlayText.startAnimation(fadeIn);
        overlayText.startAnimation(fadeOut);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(1000 + fadeIn.getStartOffset());

    }

    void loadGame(){
        myHandler = new Handler(this);

        //clear guessCounter
        guessCounter = 0;

        //clear guessedSequence
        guessedSequence.clear();

        //clear totalCorrectGuesses
        totalCorrectGuesses = 0;

        final TextView overlayText = (TextView) findViewById(R.id.overlayText);

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1000);

        in.setAnimationListener(new Animation.AnimationListener() {

            TextView overlayText = (TextView) findViewById(R.id.overlayText);

            @Override
            public void onAnimationStart(Animation animation) {
                overlayText.setText("Level "+getCurrentLevel());
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
                        MyUIHandlerThread.triggerAnimations(getCurrentLevel());
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
                saveGuess(1);
                verifyGuess();
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton(view);
                saveGuess(2);
                verifyGuess();

            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton(view);
                saveGuess(3);
                verifyGuess();
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton(view);
                saveGuess(4);
                verifyGuess();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        loadGame();
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
            case 5:
                changeTurnoverlayText();
                break;
            default:
                break;
        }

        return false;
    }

}

