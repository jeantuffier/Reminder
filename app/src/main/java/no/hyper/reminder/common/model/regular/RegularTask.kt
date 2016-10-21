package no.hyper.reminder.common.model.regular

import no.hyper.reminder.common.model.Task
import no.hyper.reminder.common.model.ViewTypeFactory

/**
 * Created by jean on 14.10.2016.
 */

data class RegularTask(private val name: String, private val priority: Int) : Task {

    override fun getViewType(factory: ViewTypeFactory) = factory.type(this)

    override fun getName() = name

    override fun getPriority() = priority

}