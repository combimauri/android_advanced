package com.example.mauricioarce.curioso;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Mauricio Arce on 28/10/2015.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
    }
}
