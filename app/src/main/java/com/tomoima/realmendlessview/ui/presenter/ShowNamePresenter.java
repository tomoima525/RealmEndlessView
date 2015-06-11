package com.tomoima.realmendlessview.ui.presenter;

import com.tomoima.realmendlessview.domain.models.SimpleData;
import com.tomoima.realmendlessview.domain.usecase.GetInitSimpleDataUseCase;
import com.tomoima.realmendlessview.domain.usecase.GetSimpleDataUseCase;
import com.tomoima.realmendlessview.event.OnSimpleDataLoadedEvent;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by tomoaki on 2015/06/01.
 */
public class ShowNamePresenter extends Presenter {

    private int mLastId;
    //private Realm mRealm;
    private GetSimpleDataUseCase mGetSimpleDataUseCase;
    private GetInitSimpleDataUseCase mGetInitSimpleDataUseCase;
    private View mView;

    @Inject
    public ShowNamePresenter(GetInitSimpleDataUseCase getInitSimpleDataUseCase, GetSimpleDataUseCase getSimpleDataUseCase){
//        Realm.deleteRealmFile(context);
//        mRealm = Realm.getInstance(context);
        mGetInitSimpleDataUseCase = getInitSimpleDataUseCase;
        mGetSimpleDataUseCase = getSimpleDataUseCase;
    }

    public void setView(View view){
        mView = view;
    }

    @Override
    public void initialize() {
        mGetSimpleDataUseCase.run();
//        RealmQuery<SimpleData> realmQuery = mRealm.where(SimpleData.class);
//        RealmResults<SimpleData> realmResults = realmQuery.findAll();
//        realmResults.sort("id");
//        int firstId = realmResults.get(0).getId();
//        mLastId = realmResults.get(realmResults.size() > RESULT_NUM ? RESULT_NUM : realmResults.size() - 1).getId();
//        RealmResults<SimpleData> partialResults = realmResults.where().between("id", firstId, mLastId).findAll();
//        partialResults.sort("id");

        //mView.addNewData(partialResults);
    }

    public boolean addNewData(){
        mGetInitSimpleDataUseCase.run();
//        RealmQuery<SimpleData> realmQuery = mRealm.where(SimpleData.class);
//        RealmResults<SimpleData> realmResults = realmQuery.greaterThan("id", mLastId).findAll();
//        if(realmResults.size() == 0){
//            Log.d(TAG, "¥¥no more data!");
//            return false;
//        }
//        realmResults.sort("id");
//        int nextLastId = realmResults.get(realmResults.size() > RESULT_NUM ? RESULT_NUM : realmResults.size() - 1).getId();
//        RealmResults<SimpleData> partialResults = realmResults.where().between("id", mLastId, nextLastId).findAll();
//        partialResults.sort("id");
//        mView.addNewData(partialResults);
//        mLastId = nextLastId;
        return true;
    }

    @Override
    public void resume() {
        EventBus.getDefault().isRegistered(this);
    }

    @Override
    public void pause() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void destroy() {
        //mRealm.close();
    }

    public void onEvent(OnSimpleDataLoadedEvent event){
        if(event.simpleDataList == null){
            mView.noMoreData();
        } else {
            mView.addNewData(event.simpleDataList);
        }
    }

    public interface View {
        void addNewData(List<SimpleData> data);
        void noMoreData();
    }
}
