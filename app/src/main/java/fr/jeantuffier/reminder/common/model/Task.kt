package fr.jeantuffier.reminder.common.model

/**
 * Created by jean on 14.10.2016.
 */

data class Task(
        val id: String,
        val title: String,
        val priority: Priority,
        val delay: Int,
        val frequency: Int,
        val fromTime: String,
        val toTime: String
) {

    companion object {
        val TITLE = "Task.TITLE"
        val ID = "Task.ID"

        val HOURS = 0
        val MINUTES = 1
    }

}