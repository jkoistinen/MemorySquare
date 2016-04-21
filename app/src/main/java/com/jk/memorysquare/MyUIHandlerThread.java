package com.jk.memorysquare;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * Created by jk on 19/04/16.
 */
public class MyUIHandlerThread extends HandlerThread implements Handler.Callback {

    private static Handler handler, callback;

    public MyUIHandlerThread(String name) {
        super(name);
        // Not used
    }

    public MyUIHandlerThread(String name, int priority) {
        super(name, priority);
        // Not used
    }

    public void setCallback(Handler cb) {
        callback = cb;
    }

    @Override
    protected void onLooperPrepared() {
        handler = new Handler(getLooper(), this);
    }

    @Override
    public boolean handleMessage(Message msg) {

        int level = msg.what;

        int delay = 0; //delay in millisecond
        for(int i = 0; i < level; i++) {

            int x = Character.getNumericValue(PlayGameActivity.levelSequence.charAt(i));

            callback.sendMessageDelayed(Message.obtain(null, 1, x), delay);

            delay = delay + 1000; //add 1000ms to next animation

            //This below animates overlayText view to indicate a turn change in the game

            // send it 5 and that will trigger the switchcase in PlayGameActivity
            if(i == level-1) {
                callback.sendMessageDelayed(Message.obtain(null, 1, 5), delay);
            }

        }
    return true;
    }

    public static void triggerAnimations(int level) {
        handler.sendEmptyMessage(level);
    }
}