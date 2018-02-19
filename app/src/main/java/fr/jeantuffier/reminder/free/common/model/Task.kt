package fr.jeantuffier.reminder.free.common.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "priority")
    val priority: Int,

    @ColumnInfo(name = "delay")
    val delay: Int,

    @ColumnInfo(name = "frequency")
    val frequency: String,

    @ColumnInfo(name = "fromTime")
    val fromTime: String,

    @ColumnInfo(name = "toTime")
    val toTime: String,

    @ColumnInfo(name = "createdAtTime")
    val createdAtTime: String
) {

    companion object {
        const val ID = "Task.ID"
        const val TITLE = "Task.TITLE"
        const val FROM = "Task.FROM"
        const val TO = "Task.TO"
        const val HOURS = "hours"
        const val PRIORITY = "Task.PRIORITY"
    }

    fun getDelayInMs() = Frequency.valueOf(frequency.toUpperCase()).getTimeInMs(delay)

}
