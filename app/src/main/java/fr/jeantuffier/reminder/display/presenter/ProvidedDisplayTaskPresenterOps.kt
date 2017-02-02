package fr.jeantuffier.reminder.display.presenter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Jean on 10/12/2016.
 */
interface ProvidedDisplayTaskPresenterOps {
    fun createDatabase()
    fun loadData()
    fun getTasksCount() : Int
    fun createViewHolder(parent: ViewGroup?, viewType: Int) : RecyclerView.ViewHolder
    fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    fun deleteItem(position: Int)
}