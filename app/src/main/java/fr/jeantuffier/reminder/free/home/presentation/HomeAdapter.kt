package fr.jeantuffier.reminder.free.home.presentation

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.home.delegate.RegularTaskDelegate

class HomeAdapter(private val tasks: List<Task>, private val listener: View.OnLongClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val regularTaskDelegate = RegularTaskDelegate()

    override fun getItemCount() = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = regularTaskDelegate.createViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = tasks[position]
        regularTaskDelegate.bindViewHolder(holder, task)
        holder.itemView.tag = task.id
        listener.onLongClick(holder.itemView)
    }
}
