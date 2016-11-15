package no.hyper.reminder.display.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_task_activity.*
import no.hyper.reminder.R
import no.hyper.reminder.common.Reminder
import no.hyper.reminder.common.extension.getInteger
import no.hyper.reminder.common.extension.toDp
import no.hyper.reminder.display.injection.DisplayTaskActivityModule
import no.hyper.reminder.common.recycler.SpaceItemDecoration
import no.hyper.reminder.create.view.activity.CreateTaskActivity
import no.hyper.reminder.display.presenter.ProvidedDisplayTaskPresenterOps
import javax.inject.Inject

class DisplayTaskActivity : AppCompatActivity(), RequiredDisplayTaskViewOps {

    val LOG_TAG = this.javaClass.simpleName

    @Inject
    lateinit var presenter : ProvidedDisplayTaskPresenterOps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_task_activity)

        setComponent()
        setToolbar()
        setRecyclerView()

        task_create_button.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivityForResult(intent, getInteger(R.integer.request_create_task))
        }

        presenter.loadData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == getInteger(R.integer.request_create_task) &&
                resultCode == getInteger(R.integer.result_create_task_success)) {
            notifyItemInserted()
        }
    }

    override fun getActivityContext() = this

    private fun setComponent() {
        Reminder.get(this).component
                .getTaskListComponent(DisplayTaskActivityModule(this))
                .inject(this)
    }

    private fun setToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setRecyclerView() {
        val layout = LinearLayoutManager(this)
        task_recycler.layoutManager = layout
        task_recycler.addItemDecoration(SpaceItemDecoration(16.toDp(this)))
        task_recycler.adapter = TaskAdapter()
    }

    private fun notifyItemInserted() {
        presenter.loadData()
        val count = task_recycler.adapter.itemCount
        task_recycler.adapter.notifyItemInserted(count)
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
