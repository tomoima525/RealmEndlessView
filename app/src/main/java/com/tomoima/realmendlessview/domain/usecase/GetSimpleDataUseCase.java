package com.tomoima.realmendlessview.domain.usecase;

import com.tomoima.realmendlessview.domain.repository.SimpleDataRepository;

import javax.inject.Inject;

/**
 * Created by tomoaki on 2015/06/11.
 */
public class GetSimpleDataUseCase extends UseCase {

    private final SimpleDataRepository mSimpleDataRepository;
    @Inject
    public GetSimpleDataUseCase(SimpleDataRepository simpleDataRepository){
        mSimpleDataRepository = simpleDataRepository;

    }
    @Override
    public void run() {
        mSimpleDataRepository.getNextData();
    }



}
