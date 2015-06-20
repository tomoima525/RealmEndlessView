package com.tomoima.realmendlessview.data.repository;

import android.content.Context;
import android.util.Log;

import com.tomoima.realmendlessview.Const;
import com.tomoima.realmendlessview.data.cache.LocalStorage;
import com.tomoima.realmendlessview.domain.models.SimpleData;
import com.tomoima.realmendlessview.domain.repository.SimpleDataRepository;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by tomoaki on 2015/06/11.
 */
public class SimpleDataRepositoryImpl implements SimpleDataRepository {
    final private String TAG = getClass().getSimpleName();
    private final Context mContext;
    private Realm mRealm;
    private final static int RESULT_NUM = 20;
    @Inject
    public SimpleDataRepositoryImpl(Context context){
        if (context == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        mContext = context;
        Realm.deleteRealmFile(mContext);
    }

    @Override
    public List<SimpleData> getInitSimpleData() {

        RealmResults<SimpleData> partialResults;

        mRealm = Realm.getInstance(mContext);
        RealmQuery<SimpleData> realmQuery = mRealm.where(SimpleData.class);
        RealmResults<SimpleData> realmResults = realmQuery.findAll();
        realmResults.sort("id");
        int firstId = realmResults.get(0).getId();
        int lastId = realmResults.get(realmResults.size() > RESULT_NUM ? RESULT_NUM : realmResults.size() - 1).getId();
        partialResults = realmResults.where().between("id", firstId, lastId).findAll();
        partialResults.sort("id");
        LocalStorage.putInt(Const.LAST_ID, lastId);
        return partialResults;
    }

    @Override
    public List<SimpleData> getNextData() {
        RealmResults<SimpleData> partialResults;

        mRealm = Realm.getInstance(mContext);
        int lastId = LocalStorage.getInt(Const.LAST_ID);
        if (lastId < 0) return null;
        RealmQuery<SimpleData> realmQuery = mRealm.where(SimpleData.class);
        RealmResults<SimpleData> realmResults = realmQuery.greaterThan("id", lastId).findAll();
        if (realmResults.size() == 0) {
            Log.d(TAG, "¥¥no more data!");
            return null;
        }
        realmResults.sort("id");
        int nextLastId = realmResults.get(realmResults.size() > RESULT_NUM ? RESULT_NUM : realmResults.size() - 1).getId();
        partialResults = realmResults.where().between("id", lastId, nextLastId).findAll();
        partialResults.sort("id");

        LocalStorage.putInt(Const.LAST_ID, nextLastId);

        return partialResults;
    }

    @Override
    public void close() {
        mRealm.close();
        Log.d(TAG, "¥¥realm instance closed");
    }
}
