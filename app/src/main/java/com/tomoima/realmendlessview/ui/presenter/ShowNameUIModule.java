package com.tomoima.realmendlessview.ui.presenter;

import android.content.Context;

import com.tomoima.realmendlessview.di.RootModule;
import com.tomoima.realmendlessview.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomoaki on 2015/06/07.
 */
@Module(
        injects = {
                MainActivity.class
        },
        addsTo = RootModule.class
)
public final class ShowNameUIModule {
    @Provides ShowNamePresenter provideShowNamePresenter(Context context) {
        return new ShowNamePresenter(context);
    }
}
