package com.tomoima.realmendlessview.data.cache;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by tomoaki on 2015/06/12.
 */
public class LocalStorage {
    @Inject static Application app;
    public static SharedPreferences getLocalStorage(){
        return app.getSharedPreferences("common", Context.MODE_PRIVATE);
    }

    public static int getInt(String key){
        return getLocalStorage().getInt(key, -1);
    }

    public static void putInt(String key, int num){
        getLocalStorage().edit().putInt(key,num).apply();
    }
}
