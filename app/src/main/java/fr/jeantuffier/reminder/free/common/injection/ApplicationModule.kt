package fr.jeantuffier.reminder.free.common.injection

import android.app.Application
import dagger.Module
import dagger.android.ContributesAndroidInjector
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskActivity
import fr.jeantuffier.reminder.free.home.injection.HomePresenterModule
import fr.jeantuffier.reminder.free.home.injection.HomeViewModule
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity


@Module
abstract class ApplicationModule(val application: Application) {

    @ContributesAndroidInjector(modules = [HomeViewModule::class, HomePresenterModule::class])
    abstract fun contributeHomeActivityInjector(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeCreateTaskActivityInjector(): CreateTaskActivity
}