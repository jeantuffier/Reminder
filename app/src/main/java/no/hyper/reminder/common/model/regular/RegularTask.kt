package no.hyper.reminder.common.model.regular

import no.hyper.reminder.common.model.Task
import no.hyper.reminder.common.model.ViewTypeFactory

/**
 * Created by jean on 14.10.2016.
 */

class RegularTask(val name: String, val priority: Int) : Task {

    override fun getViewType(factory: ViewTypeFactory) = factory.type(this)

}