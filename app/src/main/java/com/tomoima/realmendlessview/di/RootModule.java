package com.tomoima.realmendlessview.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.tomoima.realmendlessview.ThisApplication;
import com.tomoima.realmendlessview.adapters.SimpleArrayAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module created to work as junction of every module with an application scope.
 *
 * This module provides every application scope dependencies related with the AndroidSDK.
 *
 * Created by tomoaki on 2015/06/07.
 */
@Module(
        includes = {

        },
        injects = {
                ThisApplication.class,
                SimpleArrayAdapter.class
        }, library = true)
public class RootModule {
    private final Context context;

    public RootModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }

}
