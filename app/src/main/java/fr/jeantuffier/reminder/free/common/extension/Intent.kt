package fr.jeantuffier.reminder.free.common.extension

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

fun Intent.withExtras(block: Bundle.() -> Unit) : Intent {
    val bundle = Bundle()
    block(bundle)
    this.putExtras(bundle)
    return this
}