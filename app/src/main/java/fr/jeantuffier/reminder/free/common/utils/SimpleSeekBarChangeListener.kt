package fr.jeantuffier.reminder.free.common.utils

import android.widget.SeekBar

open class SimpleSeekBarChangeListener(
        private val block: (progress: Int) -> Unit
) : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) = block(progress)

    override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

    override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
}
