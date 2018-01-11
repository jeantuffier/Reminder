package fr.jeantuffier.reminder.free.home.injection

import dagger.Subcomponent
import dagger.android.AndroidInjector
import fr.jeantuffier.reminder.free.common.injection.ReminderModule
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity

@Subcomponent(modules = [HomeViewModule::class, HomePresenterModule::class])
interface HomeComponent : AndroidInjector<HomeActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>()
}