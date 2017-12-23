package fr.jeantuffier.reminder.free.home.delegate

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
        val layout = LayoutInflater.from(parent?.context).inflate(R.layout.display_task_view_item, parent, false)
        return RegularTaskViewHolder(layout)
    }

    fun bindViewHolder(holder: RecyclerView.ViewHolder, task: Task?) {
        if (task == null) return
        holder as RegularTaskViewHolder
        val context = holder.itemView.context

        val colorId = Priority.getByLevel(task.priority).getColorId()
        val color = ResourcesCompat.getColor(context.resources, colorId, null)

        val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(color))
        (holder.shape.background as GradientDrawable).color = colorStateList

        holder.title.text = task.title

        val frequency = if (task.frequency == Task.HOURS)
            R.string.create_task_frequency_hours else R.string.create_task_frequency_minutes
        holder.frequency.text = context.getString(R.string.task_frequency, task.delay,
                context.getString(frequency))

        val start = if (task.fromTime.isEmpty()) "00:00" else task.fromTime
        val end = if (task.toTime.isEmpty()) "23:59" else task.toTime
        holder.between.text = context.getString(R.string.task_between, start, end)
    }

}