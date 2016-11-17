package no.hyper.reminder.display.presenter

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import no.hyper.reminder.common.model.task.regular.RegularTaskViewHolder

/**
 * Created by Jean on 10/12/2016.
 */
interface ProvidedDisplayTaskPresenterOps {
    fun createDatabase()
    fun loadData()
    fun getTasksCount() : Int
    fun getViewType(position: Int) : Int
    fun createViewHolder(parent: ViewGroup?, viewType: Int) : RecyclerView.ViewHolder
    fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
}