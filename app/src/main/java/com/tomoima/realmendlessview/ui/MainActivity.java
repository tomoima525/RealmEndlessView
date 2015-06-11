package com.tomoima.realmendlessview.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.tomoima.realmendlessview.R;
import com.tomoima.realmendlessview.adapters.SimpleArrayAdapter;
import com.tomoima.realmendlessview.loader.JsonDataLoader;
import com.tomoima.realmendlessview.domain.models.SimpleData;
import com.tomoima.realmendlessview.ui.presenter.ShowNamePresenter;
import com.tomoima.realmendlessview.ui.presenter.ShowNameUIModule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements
        LoaderManager.LoaderCallbacks<Void>,EndlessListView.EndlessListener, ShowNamePresenter.View {
    @Inject ShowNamePresenter mShowNamePresenter;
    //@InjectView(R.id.endless) EndlessListView mListView;
    EndlessListView mListView;

    @Override
    protected List<Object> getModules() {
        List<Object> modules = new LinkedList<Object>();
        modules.add(new ShowNameUIModule());
        return modules;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<SimpleData> dataList = new ArrayList<SimpleData>();
        SimpleArrayAdapter arrayAdapter = new SimpleArrayAdapter(getApplicationContext(), dataList);
        mListView = (EndlessListView) findViewById(R.id.endless);
        mListView.setAdapter(arrayAdapter);
        mListView.setLoadingView(R.layout.layout_loading);
        mListView.setEndlessListener(this);
        mShowNamePresenter.setView(this);

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
        mShowNamePresenter.initialize();

    }

    @Override
    public void onLoaderReset(Loader<Void> loader) {

    }

    @Override
    public void loadData() {
        if(!mShowNamePresenter.addNewData()){
            mListView.setIsBottom(true);
        }
    }

    @Override
    public void onDestroy(){
        mShowNamePresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void addNewData(List<SimpleData> data) {
        mListView.addNewData(data);
    }
}
