package com.tomoima.realmendlessview.di;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.tomoima.realmendlessview.ThisApplication;
import com.tomoima.realmendlessview.adapters.SimpleArrayAdapter;
import com.tomoima.realmendlessview.data.DataModule;
import com.tomoima.realmendlessview.data.cache.LocalStorage;
import com.tomoima.realmendlessview.domain.DomainModule;
import com.tomoima.realmendlessview.ui.presenter.ShowNameUIModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module created to work as junction of every module with an application scope.
 * This module provides every application scope dependencies related with the AndroidSDK.
 *
 * Created by tomoaki on 2015/06/07.
 */
@Module(
        includes = {
                DataModule.class,
        },
        injects = {
                ThisApplication.class,
                SimpleArrayAdapter.class,
                DataModule.class,
                DomainModule.class,
                ShowNameUIModule.class,
                LocalStorage.class
        },
        library = true)
public class RootModule {
    //private final Context context;
    private final Application app;
    public RootModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return app;
    }
    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(app);
    }

}
