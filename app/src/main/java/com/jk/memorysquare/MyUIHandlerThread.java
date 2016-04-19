package com.jk.memorysquare;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by jk on 19/04/16.
 */
public class MyUIHandlerThread extends HandlerThread implements Handler.Callback {

    public static final int MSG_FINISHED = 100;
    public static final int MSG_COUNT_UP = 101;
    public static final int MSG_COUNT_DOWN = 102;

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
        int data1 = msg.arg1;
        int data2 = msg.arg2;
        int counter;

        switch (msg.what) {
            case MSG_COUNT_UP:
                for (counter = data1; counter < data2; counter++) {
                    //...
                }
                callback.sendMessage(Message.obtain(null, MSG_FINISHED, counter));
                break;
            case MSG_COUNT_DOWN:
                for (counter = data1; counter > data2; counter--) {
                    //...    }
                    callback.sendMessage(Message.obtain(null, MSG_FINISHED, counter));
                    break;
                }
                return true;
        }
        return true;
    }

    public static void bounceBox(int i) {

    }

    public static void querySomething(int start, int end) {
        if (start > end) {
            Message msg = Message.obtain(null,//Handler h
                    MSG_COUNT_DOWN, //int what
                    start,//int arg1
                    end); //int arg2
            handler.sendMessage(msg);
        } else if (start < end) {
            Message msg = Message.obtain(null, //Handler h
                    MSG_COUNT_UP, //int what
                    start,//int arg1
                    end); //int arg2
            handler.sendMessage(msg);
        }
    }
}