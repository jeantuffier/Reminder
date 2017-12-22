package fr.jeantuffier.reminder.free.display.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import kotlinx.android.synthetic.main.display_task_activity.*
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.free.common.Reminder
import fr.jeantuffier.reminder.free.common.extension.getInteger
import fr.jeantuffier.reminder.free.common.extension.toDp
import fr.jeantuffier.reminder.free.display.injection.DisplayTaskActivityModule
import fr.jeantuffier.reminder.free.common.recycler.SpaceItemDecoration
import fr.jeantuffier.reminder.free.create.view.activity.CreateTaskActivity
import fr.jeantuffier.reminder.free.display.presenter.ProvidedDisplayTaskPresenterOps
import javax.inject.Inject

class DisplayTaskActivity : AppCompatActivity(), RequiredDisplayTaskViewOps {

    var shouldShowLongItemClickOptions = false
    val menuIdAdd = Menu.FIRST
    val menuIdDelete = menuIdAdd + 1
    var itemPosition = -1

    @Inject
    lateinit var presenter : ProvidedDisplayTaskPresenterOps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_task_activity)

        setComponent()
        setToolbar()

        presenter.createDatabase()
        presenter.loadData()

        setRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == getInteger(R.integer.request_create_task) &&
                resultCode == getInteger(R.integer.result_create_task_success)) {
            updateRecyclerWithInsertion()
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
            R.id.menu_create_task -> createTask()
            menuIdDelete -> deleteTask()
        }
        return true
    }

    override fun getActivityContext() = this

    override fun addLongItemClickMenuOptionsFor(position: Int) {
        shouldShowLongItemClickOptions = true
        itemPosition = position
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

    private fun createTask() {
        val intent = Intent(this, CreateTaskActivity::class.java)
        startActivityForResult(intent, getInteger(R.integer.request_create_task))
    }

    private fun deleteTask() {
        presenter.deleteItem(itemPosition)
        shouldShowLongItemClickOptions = false
        invalidateOptionsMenu()
        updateRecyclerWithDeletion()
    }

    private fun updateRecyclerWithInsertion() {
        presenter.loadData()
        task_recycler.adapter.notifyDataSetChanged()
    }

    private fun updateRecyclerWithDeletion() = task_recycler.adapter.notifyItemRemoved(itemPosition)

    private inner class TaskAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemCount() = presenter.getTasksCount()

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            return presenter.createViewHolder(parent, viewType)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            presenter.bindViewHolder(holder, position)
        }

    }

}
