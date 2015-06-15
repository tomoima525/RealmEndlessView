package com.tomoima.realmendlessview.ui.presenter;

import com.tomoima.realmendlessview.di.RootModule;
import com.tomoima.realmendlessview.domain.usecase.GetInitSimpleDataUseCase;
import com.tomoima.realmendlessview.domain.usecase.GetSimpleDataUseCase;
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
        addsTo = RootModule.class,
        complete = false
)
public final class ShowNameUIModule {
    @Provides ShowNamePresenter provideShowNamePresenter(GetInitSimpleDataUseCase getInitSimpleDataUseCase, GetSimpleDataUseCase getSimpleDataUseCase) {
        return new ShowNamePresenter(getInitSimpleDataUseCase, getSimpleDataUseCase);
    }
}
