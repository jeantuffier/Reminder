package fr.jeantuffier.reminder.free.common.injection

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import fr.jeantuffier.reminder.free.create.injection.CreateTaskComponent
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskActivity
import fr.jeantuffier.reminder.free.home.injection.HomeComponent
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity

@Module(subcomponents = [HomeComponent::class, CreateTaskComponent::class])
abstract class ActivitiesModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    internal abstract fun bindHomeActivityInjectorFactory(builder: HomeComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(CreateTaskActivity::class)
    internal abstract fun bindCreateTaskActivityInjectorFactory(builder: CreateTaskComponent.Builder): AndroidInjector.Factory<out Activity>

}