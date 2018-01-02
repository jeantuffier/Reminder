package fr.jeantuffier.reminder.free.home.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.free.common.extension.toDp
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.common.recycler.SpaceItemDecoration
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskActivity
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View, View.OnLongClickListener {

    companion object {
        const val REQUEST_CREATE_TASK = 1
        const val REQUEST_SERVICE_NOTIFICATION = 2
        const val SUCCESS_CREATE_TASK = 3
    }

    private var shouldShowLongItemClickOptions = false
    private val menuIdAdd = Menu.FIRST
    private val menuIdDelete = menuIdAdd + 1
    private var itemPosition = -1
    private var itemId = -1

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setToolbar()

        setRecyclerView()
        presenter.loadData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CREATE_TASK && resultCode == SUCCESS_CREATE_TASK) {
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

    override fun onLongClick(view: View): Boolean {
        shouldShowLongItemClickOptions = true
        itemPosition = task_recycler.getChildAdapterPosition(view)
        itemId = view.tag as Int
        supportInvalidateOptionsMenu()
        return true
    }

    override fun setTasks(tasks: List<Task>) {
        task_recycler.adapter = HomeAdapter(tasks, this)
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setRecyclerView() {
        val layout = LinearLayoutManager(this)
        task_recycler.layoutManager = layout
        task_recycler.addItemDecoration(SpaceItemDecoration(16.toDp(this)))
    }

    private fun createTask() {
        val intent = Intent(this, CreateTaskActivity::class.java)
        startActivityForResult(intent, REQUEST_CREATE_TASK)
    }

    private fun deleteTask() {
        task_recycler
        presenter.deleteItem(itemId)
        shouldShowLongItemClickOptions = false
        invalidateOptionsMenu()
        updateRecyclerWithDeletion()
    }

    private fun updateRecyclerWithInsertion() {
        presenter.loadData()
        task_recycler.adapter.notifyDataSetChanged()
    }

    private fun updateRecyclerWithDeletion() = task_recycler.adapter.notifyItemRemoved(itemPosition)

}
