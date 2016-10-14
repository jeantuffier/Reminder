package no.hyper.reminder.common.model

import no.hyper.reminder.R
import no.hyper.reminder.common.model.regular.RegularTask

/**
 * Created by jean on 14.10.2016.
 */
class ViewTypeFactory {
    fun type(task: RegularTask) = R.layout.view_item_task_list
}