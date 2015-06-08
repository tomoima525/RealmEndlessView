package com.tomoima.realmendlessview.di;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module created to provide some common activity scope depdendencies as @ActivityContext.
 * This module is going to be added to the graph generated for every activity while the activity
 * creation lifecycle.
 * Created by tomoaki on 2015/06/07.
 */
@Module(library = true) public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityContext @Provides
    Context provideActivityContext() {
        return activity;
    }
}
