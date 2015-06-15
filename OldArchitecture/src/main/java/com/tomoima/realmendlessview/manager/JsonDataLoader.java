package com.tomoima.realmendlessview.manager;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tomoima.realmendlessview.models.SimpleData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by tomoaki on 2015/05/31.
 */
public class JsonDataLoader extends AsyncTaskLoader<Void> {
    final private String TAG = getClass().getSimpleName();
    private static final String DATA_NAME = "data.json";
    public JsonDataLoader(Context context) {
        super(context);
    }

    @Override
    public Void loadInBackground() {
        AssetManager assetManager = getContext().getAssets();
        InputStream file = null;
        String jsonArrayStr = null;
        List<SimpleData> array = null;

        try {
            file = assetManager.open(DATA_NAME);
            byte[] formArray = new byte[file.available()];
            file.read(formArray);
            file.close();
            jsonArrayStr = new String(formArray);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "¥¥ no files");
        }

        if(jsonArrayStr == null) return null;
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
        array =  gson.fromJson(jsonArrayStr,new TypeToken<List<SimpleData>>(){}.getType());

        Realm realm = Realm.getInstance(getContext());
        realm.beginTransaction();
        if(array == null) return null;

        realm.copyToRealm(array);
        realm.commitTransaction();
        return null;
    }
}
