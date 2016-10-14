package no.hyper.reminder.create.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_creator_task.*
import no.hyper.reminder.R
import no.hyper.reminder.list.view.RequiredTaskListViewOps

class TaskCreatorActivity : AppCompatActivity(), RequiredTaskListViewOps {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_task)

        setToolbar()
        setFab()
    }

    override fun getActivityContext() = this

    override fun notifyItemInserted() {
        Snackbar.make(task_creator_root, getString(R.string.add_task_success), Snackbar.LENGTH_LONG).show()
    }

    private fun setToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun setFab() {
        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { }
    }

}
