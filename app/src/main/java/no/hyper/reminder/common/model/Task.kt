package no.hyper.reminder.common.model

/**
 * Created by jean on 14.10.2016.
 */
interface Task {
    fun getViewType(factory: ViewTypeFactory) : Int?
    fun getName() : String
    fun getPriority() : Priority
    fun getDeadLine() : String
}