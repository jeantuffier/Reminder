package fr.jeantuffier.reminder.common.model.task

import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.common.model.task.regular.RegularTask

/**
 * Created by jean on 14.10.2016.
 */
class ViewTypeFactory {
    fun type(task: RegularTask) = R.layout.display_task_view_item
}