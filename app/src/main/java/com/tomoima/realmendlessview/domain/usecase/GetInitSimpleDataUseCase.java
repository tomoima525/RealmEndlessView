package com.tomoima.realmendlessview.domain.usecase;

import com.tomoima.realmendlessview.domain.repository.SimpleDataRepository;
import com.tomoima.realmendlessview.event.OnSimpleDataLoadedEvent;

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
        //mSimpleDataRepository.getInitSimpleData();

        EventBus.getDefault().post(new OnSimpleDataLoadedEvent(mSimpleDataRepository.getInitSimpleData()));
    }

}
