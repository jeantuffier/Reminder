package fr.jeantuffier.reminder.common.model.task

import android.os.Parcel
import android.os.Parcelable
import fr.jeantuffier.reminder.common.model.task.Task
import fr.jeantuffier.reminder.display.presenter.ViewTypeFactory
import fr.jeantuffier.reminder.common.model.timer.Timer

/**
 * Created by jean on 14.10.2016.
 */

data class RegularTask(
        private val id: String,
        private val title: String,
        private val priority: Task.Priority,
        private val timer: Timer,
        private val fromTime: String,
        private val toTime: String
) : Task {

    override fun getId() = id

    override fun getViewType(factory: ViewTypeFactory) = factory.type(this)

    override fun getTitle() = title

    override fun getPriority() = priority

    override fun getTimer() = timer

    override fun getFromTime() = fromTime

    override fun getToTime() = toTime

}