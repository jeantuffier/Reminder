package no.hyper.reminder.list.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_task_activity.*
import no.hyper.reminder.R
import no.hyper.reminder.common.Reminder
import no.hyper.reminder.common.extension.getInteger
import no.hyper.reminder.common.extension.toDp
import no.hyper.reminder.list.injection.TaskListActivityModule
import no.hyper.reminder.common.recycler.SpaceItemDecoration
import no.hyper.reminder.create.view.activity.CreateTaskActivity
import no.hyper.reminder.list.presenter.ProvidedTaskListPresenterOps
import javax.inject.Inject

class TaskListActivity : AppCompatActivity(), RequiredTaskListViewOps {

    lateinit var taskRecycler : RecyclerView

    @Inject
    lateinit var presenter : ProvidedTaskListPresenterOps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_task_activity)

        taskRecycler = task_recycler
        task_create_button.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivityForResult(intent, getInteger(R.integer.request_create_task))
        }

        setComponent()
        setToolbar()
        setRecyclerView()
    }

    override fun getActivityContext() = this

    override fun notifyItemInserted() { }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == getInteger(R.integer.request_create_task) &&
                resultCode == getInteger(R.integer.result_create_task_success)) {

        }
    }

    private fun setComponent() {
        Reminder.get(this).component
                .getTaskListComponent(TaskListActivityModule(this))
                .inject(this)
    }

    private fun setToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setRecyclerView() {
        val layout = LinearLayoutManager(this)
        taskRecycler.layoutManager = layout
        taskRecycler.addItemDecoration(SpaceItemDecoration(16.toDp(this)))
        taskRecycler.adapter = TaskAdapter()
    }

    private inner class TaskAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
