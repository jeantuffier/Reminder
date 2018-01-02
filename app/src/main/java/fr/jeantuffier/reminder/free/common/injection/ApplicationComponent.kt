package fr.jeantuffier.reminder.free.common.injection

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import fr.jeantuffier.reminder.free.common.Reminder
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<Reminder> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Reminder>()

}