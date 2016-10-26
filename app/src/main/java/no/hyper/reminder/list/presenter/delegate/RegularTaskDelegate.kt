package no.hyper.reminder.list.presenter.delegate

import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import no.hyper.reminder.common.model.Task
import no.hyper.reminder.common.model.regular.RegularTaskViewHolder

/**
 * Created by jean on 14.10.2016.
 */
class RegularTaskDelegate {

    private val LOG_TAG = this.javaClass.simpleName

    fun createViewHolder(parent: ViewGroup?, viewType: Int): RegularTaskViewHolder {
        val layout = LayoutInflater.from(parent?.context).inflate(viewType, parent, false)
        return RegularTaskViewHolder(layout)
    }

    fun bindViewHolder(holder: RecyclerView.ViewHolder, task: Task?) {
        if (task == null) return
        holder as RegularTaskViewHolder
        val context = holder.itemView.context

        val colorId = task.getPriority().getColorId()
        val color = ResourcesCompat.getColor(context.resources, colorId, null)
        holder.backgroundIcon.setBackgroundColor(color)

        val drawableId = task.getPriority().getIconId()
        val drawable = ResourcesCompat.getDrawable(context.resources, drawableId, null)
        holder.icon.setImageDrawable(drawable)

        holder.title.text = task.getName()
    }

}