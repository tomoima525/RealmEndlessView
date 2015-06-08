package com.tomoima.realmendlessview.ui.presenter;

import android.content.Context;
import android.util.Log;

import com.tomoima.realmendlessview.models.SimpleData;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by tomoaki on 2015/06/01.
 */
public class ShowNamePresenter extends Presenter {
    final private String TAG = getClass().getSimpleName();
    private int mLastId;
    private Realm mRealm;
    private final static int RESULT_NUM = 20;
    private View mView;

    @Inject
    public ShowNamePresenter(Context context){
        Realm.deleteRealmFile(context);
        mRealm = Realm.getInstance(context);
    }

    public void setView(View view){
        mView = view;
    }

    @Override
    public void initialize() {
        RealmQuery<SimpleData> realmQuery = mRealm.where(SimpleData.class);
        RealmResults<SimpleData> realmResults = realmQuery.findAll();
        realmResults.sort("id");
        int firstId = realmResults.get(0).getId();
        mLastId = realmResults.get(realmResults.size() > RESULT_NUM ? RESULT_NUM : realmResults.size() - 1).getId();
        RealmResults<SimpleData> partialResults = realmResults.where().between("id", firstId, mLastId).findAll();
        partialResults.sort("id");
        mView.addNewData(partialResults);
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
        mView.addNewData(partialResults);
        mLastId = nextLastId;
        return true;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        Log.d(TAG, "¥¥realm instance closed");
        mRealm.close();
    }

    public interface View {
        void addNewData(List<SimpleData> data);
    }
}
