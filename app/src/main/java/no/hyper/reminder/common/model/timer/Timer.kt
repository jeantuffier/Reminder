package no.hyper.reminder.common.model.timer

/**
 * Created by jean on 01.11.2016.
 */
data class Timer (
        val frequency: Frequency,
        val delay: Int,
        val start: String,
        val end: String,
        val alarm: Alarm
) {

    enum class Frequency { HOURS, MINUTES }

    enum class Alarm { NOTIFICATION, VIBRATION }

}