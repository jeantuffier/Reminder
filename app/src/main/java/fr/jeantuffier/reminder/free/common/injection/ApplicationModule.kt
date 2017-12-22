package fr.jeantuffier.reminder.common.injection

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