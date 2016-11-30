package fr.jeantuffier.reminder.common.model.timer

/**
 * Created by jean on 01.11.2016.
 */
data class Timer (
        val id: String,
        val frequency: Frequency,
        val delay: Int,
        val alarm: Alarm = Timer.Alarm.NOTIFICATION,
        val activated: Boolean = true
) {

    enum class Frequency { HOURS, MINUTES }

    enum class Alarm { NOTIFICATION, VIBRATION }

}