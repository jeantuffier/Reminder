package fr.jeantuffier.reminder.free.home.injection

import dagger.Subcomponent
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity

@ActivityScope
@Subcomponent( modules = [HomeActivityModule::class])
interface HomeActivityComponent {
    fun inject(activity: HomeActivity)
}