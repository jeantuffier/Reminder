package no.hyper.reminder.display.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
    var shouldShowLongItemClickOptions = false
    val menuIdAdd = Menu.FIRST
    val menuIdDelete = menuIdAdd + 1
    var selectedId : Long = 0

    @Inject
    lateinit var presenter : ProvidedDisplayTaskPresenterOps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_task_activity)

        setComponent()
        setToolbar()
        setRecyclerView()

        presenter.createDatabase()
        presenter.loadData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == getInteger(R.integer.request_create_task) &&
                resultCode == getInteger(R.integer.result_create_task_success)) {
            updateRecycler()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (shouldShowLongItemClickOptions) {
            menu?.add(0, menuIdDelete, Menu.NONE, R.string.task_delete)
                    ?.setIcon(R.drawable.ic_delete)
                    ?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.display, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_create_task -> {
                val intent = Intent(this, CreateTaskActivity::class.java)
                startActivityForResult(intent, getInteger(R.integer.request_create_task))
            }
            menuIdDelete -> presenter.deleteItem(selectedId)
        }
        return true
    }

    override fun getActivityContext() = this

    override fun addLongItemClickMenuOptionsFor(itemId: Long) {
        shouldShowLongItemClickOptions = true
        selectedId = itemId
        supportInvalidateOptionsMenu()
    }

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

    private fun updateRecycler() {
        presenter.loadData()
        val position = presenter.getTasksCount()
        task_recycler.adapter.notifyItemInserted(position)
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
