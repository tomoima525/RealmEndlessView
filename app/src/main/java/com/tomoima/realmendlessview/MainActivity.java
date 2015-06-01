package com.tomoima.realmendlessview;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.tomoima.realmendlessview.adapters.SimpleArrayAdapter;
import com.tomoima.realmendlessview.manager.JsonDataLoader;
import com.tomoima.realmendlessview.models.SimpleData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void>,EndlessListView.EndlessListener {
    private List<SimpleData> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EndlessListView listView = (EndlessListView) findViewById(R.id.endless);
        mDataList = new ArrayList<SimpleData>();
        SimpleArrayAdapter arrayAdapter = new SimpleArrayAdapter(getApplicationContext(), mDataList);
        listView.setAdapter(arrayAdapter);
        listView.setLoadingView(R.layout.layout_loading);
        //Set listview, datalist to Manager

        //LoadData from json to Realm
        getSupportLoaderManager().initLoader(0, null,this);
    }

    @Override
    public Loader<Void> onCreateLoader(int id, Bundle args) {
        JsonDataLoader jsonDataLoader = new JsonDataLoader(getApplicationContext());
        jsonDataLoader.forceLoad();
        return jsonDataLoader;
    }

    @Override
    public void onLoadFinished(Loader<Void> loader, Void data) {
        //set data to adapter

    }

    @Override
    public void onLoaderReset(Loader<Void> loader) {

    }

    @Override
    public void loadData() {

    }
}
