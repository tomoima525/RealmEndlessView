package com.tomoima.realmendlessview.manager;

import android.content.Context;
import android.util.Log;

import com.tomoima.realmendlessview.EndlessListView;
import com.tomoima.realmendlessview.models.SimpleData;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by tomoaki on 2015/06/01.
 */
public class DataHandleManager {
    final private String TAG = getClass().getSimpleName();
    private Context mContext;
    private EndlessListView mListView;
    private int mLastId;
    private Realm mRealm;

    private final static int RESULT_NUM = 20;
    public DataHandleManager(Context context, EndlessListView listView){
        mListView = listView;
        mContext = context;
        Realm.deleteRealmFile(mContext);
        mRealm = Realm.getInstance(mContext);
    }

    public void setInitData(){
        RealmQuery<SimpleData> realmQuery = mRealm.where(SimpleData.class);
        RealmResults<SimpleData> realmResults = realmQuery.findAll();
        realmResults.sort("id");
        int firstId = realmResults.get(0).getId();
        mLastId = realmResults.get(realmResults.size() > RESULT_NUM ? RESULT_NUM : realmResults.size() - 1).getId();
        RealmResults<SimpleData> partialResults = realmResults.where().between("id", firstId, mLastId).findAll();
        partialResults.sort("id");

        mListView.addNewData(partialResults);

    }

    public boolean addNewData(){
        RealmQuery<SimpleData> realmQuery = mRealm.where(SimpleData.class);
        RealmResults<SimpleData> realmResults = realmQuery.greaterThan("id", mLastId).findAll();
        if(realmResults.size() == 0){
            Log.d(TAG, "¥¥no more data!");
            return false;
        }
        realmResults.sort("id");
        int nextLastId = realmResults.get(realmResults.size() > RESULT_NUM ? RESULT_NUM : realmResults.size() - 1).getId();
        RealmResults<SimpleData> partialResults = realmResults.where().between("id", mLastId, nextLastId).findAll();
        partialResults.sort("id");
        mListView.addNewData(partialResults);
        mLastId = nextLastId;
        return true;
    }

    public void destroyRealm(){
        Log.d(TAG, "¥¥realm instance closed");
        mRealm.close();
    }

}
