package fr.jeantuffier.reminder.display.presenter.delegate

import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.common.model.Task
import fr.jeantuffier.reminder.display.model.viewholder.RegularTaskViewHolder

/**
 * Created by jean on 14.10.2016.
 */
class RegularTaskDelegate {

    fun createViewHolder(parent: ViewGroup?, viewType: Int): RegularTaskViewHolder {
        val layout = LayoutInflater.from(parent?.context).inflate(R.layout.display_task_view_item, parent, false)
        return RegularTaskViewHolder(layout)
    }

    fun bindViewHolder(holder: RecyclerView.ViewHolder, task: Task?) {
        if (task == null) return
        holder as RegularTaskViewHolder
        val context = holder.itemView.context

        val drawableId = task.priority.getIconId()
        val drawable = ResourcesCompat.getDrawable(context.resources, drawableId, null)
        holder.icon.setImageDrawable(drawable)

        val colorId = task.priority.getColorId()
        val color = ResourcesCompat.getColor(context.resources, colorId, null)
        holder.icon.setBackgroundColor(color)

        holder.title.text = task.title

        val frequency = if (task.frequency == Task.HOURS)
            R.string.create_task_frequency_hours else R.string.create_task_frequency_minutes
        holder.frequency.text = context.getString(R.string.task_frequency, task.delay,
                context.getString(frequency))
    }

}