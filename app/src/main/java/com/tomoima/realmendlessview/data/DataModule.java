package com.tomoima.realmendlessview.data;

import android.content.Context;

import com.tomoima.realmendlessview.data.repository.SimpleDataRepositoryImpl;
import com.tomoima.realmendlessview.domain.repository.SimpleDataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomoaki on 2015/06/13.
 */
@Module(
        injects = {
                SimpleDataRepositoryImpl.class,
        },

        complete = false, library = true
)
public class DataModule {
    @Provides
    @Singleton
    SimpleDataRepositoryImpl provideSimpleDataRepositoryImpl(Context context){
        return new SimpleDataRepositoryImpl(context);
    }

    @Provides
    @Singleton
    SimpleDataRepository provideSimpleDataRepository(SimpleDataRepositoryImpl simpleDataRepository){
        return simpleDataRepository;
    }

}
