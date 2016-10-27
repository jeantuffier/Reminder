package no.hyper.reminder.common.model.regular

import no.hyper.reminder.common.model.Priority
import no.hyper.reminder.common.model.Task
import no.hyper.reminder.common.model.ViewTypeFactory

/**
 * Created by jean on 14.10.2016.
 */

data class RegularTask(
        private val name: String,
        private val priority: Priority,
        private val deadLine: String
) : Task {

    override fun getViewType(factory: ViewTypeFactory) = factory.type(this)

    override fun getName() = name

    override fun getPriority() = priority

    override fun getDeadLine() = deadLine

}