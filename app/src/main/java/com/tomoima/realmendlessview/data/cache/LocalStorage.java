package com.tomoima.realmendlessview.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.tomoima.realmendlessview.ThisApplication;

/**
 * Created by tomoaki on 2015/06/12.
 */
public class LocalStorage {

    public static SharedPreferences getLocalStorage(){
        return ThisApplication.getThisApplication().getSharedPreferences("common", Context.MODE_PRIVATE);
    }

    public static int getInt(String key){
        return getLocalStorage().getInt(key, -1);
    }

    public static void putInt(String key, int num){
        getLocalStorage().edit().putInt(key,num).apply();
    }
}
