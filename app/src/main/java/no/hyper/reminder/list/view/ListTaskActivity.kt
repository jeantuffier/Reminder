package no.hyper.reminder.list.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_list_task.*
import no.hyper.reminder.R
import no.hyper.reminder.common.model.regular.RegularTaskViewHolder
import no.hyper.reminder.list.presenter.ProvidedTaskListPresenterOps

class ListTaskActivity : AppCompatActivity() {

    private lateinit var taskRecycler : RecyclerView
    private lateinit var presenter : ProvidedTaskListPresenterOps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_task)

        getUiElement()
        setRecyclerView()
    }

    private fun getUiElement() {
        taskRecycler = task_recycler
    }

    private fun setRecyclerView() {
        val layout = LinearLayoutManager(this)
        taskRecycler.layoutManager = layout

        taskRecycler.adapter = TaskAdapter()
    }

    private inner class TaskAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemCount() = presenter.getTasksCount()

        override fun getItemViewType(position: Int) = presenter.getViewType(position)

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            return presenter.createViewHolder(parent, viewType)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            presenter.bindViewHolder(holder, position)
        }
    }
}
