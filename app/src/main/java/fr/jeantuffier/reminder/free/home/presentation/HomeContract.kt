package fr.jeantuffier.reminder.free.home.presentation

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import fr.jeantuffier.reminder.free.common.model.Task

class HomeContract {

    interface View {
        fun addLongItemClickMenuOptionsFor(position: Int)
    }

    interface Model {
        fun createDatabase()
        fun getTaskCount(): Int
        fun getTask(position: Int): Task?
        fun getPosition(task: Task): Int?
        fun saveTask(task: Task): Long
        fun loadData()
        fun deleteTask(position: Int)
    }

    interface Presenter {
        fun createDatabase()
        fun loadData()
        fun getTasksCount(): Int
        fun createViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder
        fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
        fun deleteItem(position: Int)
    }
}