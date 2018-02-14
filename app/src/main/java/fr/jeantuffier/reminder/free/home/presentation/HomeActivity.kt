package fr.jeantuffier.reminder.free.home.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import dagger.android.AndroidInjection
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskActivity
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View {

    companion object {
        const val REQUEST_CREATE_TASK = 1
        const val SUCCESS_CREATE_TASK = 10
    }

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setToolbar()
        setRecyclerView()

        presenter.loadData()
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        val divider = DividerItemDecoration(this, LinearLayout.VERTICAL)
        recyclerView.addItemDecoration(divider)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CREATE_TASK && resultCode == SUCCESS_CREATE_TASK) {
            presenter.loadData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.display, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_create_task) {
            createTask()
        }
        return true
    }

    override fun setTasks(tasks: List<Task>) {
        recyclerView.adapter = HomeAdapter(tasks)
    }

    private fun createTask() {
        val intent = Intent(this, CreateTaskActivity::class.java)
        startActivityForResult(intent, REQUEST_CREATE_TASK)
    }

    override fun deleteTask(id: Int) {
        presenter.loadData()
    }
}
