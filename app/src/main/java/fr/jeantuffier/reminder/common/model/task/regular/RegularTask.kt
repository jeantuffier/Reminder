package fr.jeantuffier.reminder.common.model.task.regular

import android.os.Parcel
import android.os.Parcelable
import fr.jeantuffier.reminder.common.model.task.Task
import fr.jeantuffier.reminder.common.model.task.ViewTypeFactory
import fr.jeantuffier.reminder.common.model.timer.Timer

/**
 * Created by jean on 14.10.2016.
 */

data class RegularTask(
        private val id: String,
        private val title: String,
        private val priority: Task.Priority,
        private val timer: Timer
) : Task {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RegularTask> = object : Parcelable.Creator<RegularTask> {
            override fun createFromParcel(parcel: Parcel) = RegularTask(parcel)

            override fun newArray(size: Int): Array<RegularTask?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(parcel: Parcel) : this (
            parcel.readString(),
            parcel.readString(),
            Task.Priority.valueOf(parcel.readString()),
            Timer (
                parcel.readString(),
                Timer.Frequency.valueOf(parcel.readString()),
                parcel.readInt(),
                Timer.Alarm.valueOf(parcel.readString())
            )
    )

    override fun getId() = id

    override fun getViewType(factory: ViewTypeFactory) = factory.type(this)

    override fun getTitle() = title

    override fun getPriority() = priority

    override fun getTimer() = timer

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(priority.toString())
        parcel.writeString(timer.frequency.toString())
        parcel.writeInt(timer.delay)
        parcel.writeString(timer.alarm.toString())
    }

    override fun describeContents() = 0

}