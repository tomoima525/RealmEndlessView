package com.tomoima.realmendlessview.domain.usecase;

import com.tomoima.realmendlessview.Const;
import com.tomoima.realmendlessview.domain.models.SimpleData;
import com.tomoima.realmendlessview.domain.repository.SimpleDataRepository;
import com.tomoima.realmendlessview.event.OnSimpleDataLoadedEvent;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by tomoaki on 2015/06/11.
 */
public class GetInitSimpleDataUseCase extends UseCase {
    private final SimpleDataRepository mSimpleDataRepository;
    @Inject
    public GetInitSimpleDataUseCase(SimpleDataRepository simpleDataRepository){
        mSimpleDataRepository = simpleDataRepository;

    }

    @Override
    public void run() {
        List<SimpleData> data = mSimpleDataRepository.getInitSimpleData();
        int response = Const.RESPONSE_OK;
        if(data == null){
            response = Const.RESPONSE_FALSE;
        }
        EventBus.getDefault().post(new OnSimpleDataLoadedEvent(data,response));
    }

    public void close(){
        mSimpleDataRepository.close();
    }

}
