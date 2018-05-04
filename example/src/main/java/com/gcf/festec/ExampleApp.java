package com.gcf.festec;

import android.app.Application;

import com.gcf.latte_core.app.Latte;

/**
 * Created by guchenfei
 * on 04/05/18
 */
public class ExampleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withApiHost("http://127.0.0.1/").configure();
    }
}
