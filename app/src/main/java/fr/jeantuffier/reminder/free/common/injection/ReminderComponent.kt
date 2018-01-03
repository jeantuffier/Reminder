package fr.jeantuffier.reminder.free.common.injection

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import fr.jeantuffier.reminder.free.common.Reminder
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ReminderModule::class])
interface ReminderComponent : AndroidInjector<Reminder>