package com.tomoima.realmendlessview.ui.presenter;

import com.tomoima.realmendlessview.Const;
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

    private GetSimpleDataUseCase mGetSimpleDataUseCase;
    private GetInitSimpleDataUseCase mGetInitSimpleDataUseCase;
    private View mView;

    @Inject
    public ShowNamePresenter(GetInitSimpleDataUseCase getInitSimpleDataUseCase, GetSimpleDataUseCase getSimpleDataUseCase){
        mGetInitSimpleDataUseCase = getInitSimpleDataUseCase;
        mGetSimpleDataUseCase = getSimpleDataUseCase;
    }

    public void setView(View view){
        mView = view;
    }

    @Override
    public void initialize() {
        mGetInitSimpleDataUseCase.run();
    }

    public boolean addNewData(){
        mGetSimpleDataUseCase.run();
        return true;
    }

    @Override
    public void resume() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void pause() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void destroy() {
        mGetInitSimpleDataUseCase.close();
        mGetSimpleDataUseCase.close();
    }

    public void onEvent(OnSimpleDataLoadedEvent event){
        if(event.response == Const.RESPONSE_FALSE){
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
