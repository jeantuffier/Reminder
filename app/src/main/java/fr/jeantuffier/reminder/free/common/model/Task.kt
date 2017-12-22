package fr.jeantuffier.reminder.common.model

/**
 * Created by jean on 14.10.2016.
 */

data class Task(
        val id: String,
        val title: String,
        val priority: Priority,
        val delay: Int,
        val frequency: String,
        val fromTime: String,
        val toTime: String,
        val createdAtTime: String
) {

    companion object {
        val ID = "Task.ID"
        val TITLE = "Task.TITLE"
        val FROM = "Task.FROM"
        val TO = "Task.TO"
        val HOURS = "hours"
    }

}