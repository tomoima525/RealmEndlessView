package com.tomoima.realmendlessview.manager;

import android.content.Context;

import com.tomoima.realmendlessview.adapters.SimpleArrayAdapter;
import com.tomoima.realmendlessview.models.SimpleData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by tomoaki on 2015/06/01.
 */
public class DataHandleManager {
    private Context mContext;
    private SimpleArrayAdapter mAdapter;
    private List<SimpleData> mDataList;
    private final static int RESULT_NUM = 20;
    DataHandleManager(Context context,SimpleArrayAdapter adapter, List<SimpleData> dataList){
        mAdapter = adapter;
        mDataList = dataList;
        mContext = context;
    }

    public void setInitData(){
        Realm realm = Realm.getInstance(mContext);
        RealmQuery<SimpleData> realmQuery = realm.where(SimpleData.class);


    }

    public void addNewData(){

    }




}
