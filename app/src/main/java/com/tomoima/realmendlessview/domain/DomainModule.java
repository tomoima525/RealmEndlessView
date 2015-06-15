package com.tomoima.realmendlessview.domain;

import com.tomoima.realmendlessview.di.RootModule;
import com.tomoima.realmendlessview.domain.repository.SimpleDataRepository;
import com.tomoima.realmendlessview.domain.usecase.GetInitSimpleDataUseCase;
import com.tomoima.realmendlessview.domain.usecase.GetSimpleDataUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomoaki on 2015/06/13.
 */
@Module(
        injects = {
                GetSimpleDataUseCase.class,
                GetInitSimpleDataUseCase.class
        },
        addsTo = RootModule.class,
        complete = false,
        library = true
)
public class DomainModule {
    @Provides
    @Singleton
    GetInitSimpleDataUseCase provideGetInitSimpleDataUseCase(SimpleDataRepository simpleDataRepository){
        return new GetInitSimpleDataUseCase(simpleDataRepository);
    }

    @Provides
    @Singleton
    GetSimpleDataUseCase provideGetSimpleDataUseCase(SimpleDataRepository simpleDataRepository){
        return new GetSimpleDataUseCase(simpleDataRepository);
    }

}
