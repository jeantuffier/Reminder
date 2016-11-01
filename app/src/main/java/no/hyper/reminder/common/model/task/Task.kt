package no.hyper.reminder.common.model.task

import no.hyper.reminder.common.model.timer.Timer

/**
 * Created by jean on 14.10.2016.
 */
interface Task {
    fun getViewType(factory: ViewTypeFactory) : Int?
    fun getTitle() : String
    fun getPriority() : Priority
    fun getDeadLine() : String
    fun getTimer() : Timer
}