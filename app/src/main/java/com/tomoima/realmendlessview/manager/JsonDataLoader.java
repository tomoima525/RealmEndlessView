package com.tomoima.realmendlessview.manager;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.Gson;
import com.tomoima.realmendlessview.models.SimpleData;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;

/**
 * Created by tomoaki on 2015/05/31.
 */
public class JsonDataLoader extends AsyncTaskLoader<Void> {
    private static final String DATA_NAME = "data.json";
    public JsonDataLoader(Context context) {
        super(context);
    }

    @Override
    public Void loadInBackground() {
        AssetManager assetManager = getContext().getAssets();
        InputStream file = null;
        String jsonArrayStr = null;
        SimpleData[] array = null;
        try {
            file = assetManager.open(DATA_NAME);
            byte[] formArray = new byte[file.available()];
            file.read(formArray);
            file.close();
            jsonArrayStr = new String(formArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(jsonArrayStr != null){
            Gson gson = new Gson();
            array =  gson.fromJson(jsonArrayStr,SimpleData[].class);

        }

        Realm realm = Realm.getInstance(getContext());
        realm.beginTransaction();
        if(array == null) return null;

        for(SimpleData data : array){
            SimpleData simpleData = realm.createObject(SimpleData.class);
            simpleData.setId(data.getId());
            simpleData.setName(data.getName());
        }
        realm.commitTransaction();
        return null;
    }
}
