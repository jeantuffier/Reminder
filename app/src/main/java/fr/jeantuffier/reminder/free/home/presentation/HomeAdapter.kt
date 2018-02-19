package fr.jeantuffier.reminder.free.home.presentation

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.home.presentation.delegate.RegularTaskDelegate

class HomeAdapter(private val items: MutableList<Task>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val regularTaskDelegate = RegularTaskDelegate()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
        regularTaskDelegate.createViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = items[position]
        regularTaskDelegate.bindViewHolder(holder, task)
        holder.itemView.tag = task.id
    }

    fun addItem(task: Task) = items.add(task)

    fun removeItem(position: Int) = items.removeAt(position)

    fun setItems(tasks: List<Task>) {
        items.clear()
        items.addAll(0, tasks)
    }
}
