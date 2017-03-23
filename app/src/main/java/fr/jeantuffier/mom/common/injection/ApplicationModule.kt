package fr.jeantuffier.mom.common.injection

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jean on 25.10.2016.
 */

@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    fun providesApplication() = application

}