package fr.jeantuffier.reminder.free.home.presentation.delegate

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.free.common.model.Priority
import fr.jeantuffier.reminder.free.common.model.Task

class RegularTaskDelegate {

    fun createViewHolder(parent: ViewGroup?, viewType: Int): RegularTaskViewHolder {
        val layout = LayoutInflater.from(parent?.context)
            .inflate(R.layout.display_task_view_item, parent, false)
        return RegularTaskViewHolder(layout)
    }

    fun bindViewHolder(holder: RecyclerView.ViewHolder, task: Task?) {
        if (task == null) return
        holder as RegularTaskViewHolder

        setColor(holder, task.priority)
        holder.title.text = task.title
        setFrequency(task, holder)
        setInterval(task, holder)
    }

    private fun setColor(viewHolder: RegularTaskViewHolder, priority: Int) {
        val colorId = Priority.getByLevel(priority).getColorId()
        val color = ResourcesCompat.getColor(viewHolder.itemView.context.resources, colorId, null)
        val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(color))
        (viewHolder.shape.background as GradientDrawable).color = colorStateList
    }

    private fun setFrequency(task: Task, viewHolder: RegularTaskViewHolder) {
        val context = viewHolder.itemView.context
        val frequency = if (task.frequency == Task.HOURS) {
            R.string.create_task_frequency_hours
        } else {
            R.string.create_task_frequency_minutes
        }

        viewHolder.frequency.text = context.getString(
            R.string.task_frequency, task.delay,
            context.getString(frequency)
        )
    }

    private fun setInterval(task: Task, viewHolder: RegularTaskViewHolder) {
        viewHolder.between.text = task.fromTime
        viewHolder.and.text = task.toTime
    }

}
