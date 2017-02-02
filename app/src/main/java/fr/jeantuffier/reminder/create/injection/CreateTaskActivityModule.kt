package fr.jeantuffier.reminder.create.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.common.injection.ActivityScope
import fr.jeantuffier.reminder.create.model.CreateTaskModel
import fr.jeantuffier.reminder.create.presenter.CreateTaskPresenter
import fr.jeantuffier.reminder.create.presenter.ProvidedCreateTaskPresenterOps
import fr.jeantuffier.reminder.create.view.activity.CreateTaskActivity
import java.lang.ref.WeakReference

/**
 * Created by jean on 01.11.2016.
 */
@Module
class CreateTaskActivityModule(private val activity : CreateTaskActivity) {

    @Provides
    @ActivityScope
    fun providesCreateTaskActivity() = activity

    @Provides
    @ActivityScope
    fun providesCreateTaskPresenter() : ProvidedCreateTaskPresenterOps {
        val presenter = CreateTaskPresenter(WeakReference(activity))
        val model = CreateTaskModel(presenter)
        presenter.model = model

        return presenter
    }

}