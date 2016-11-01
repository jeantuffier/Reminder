package no.hyper.reminder.create.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.activity_creator_task.*
import no.hyper.reminder.R
import no.hyper.reminder.common.Reminder
import no.hyper.reminder.create.injection.CreateTaskActivityModule
import no.hyper.reminder.create.presenter.ProvidedCreateTaskListPresenterOps
import javax.inject.Inject

class CreateTaskActivity : AppCompatActivity(), RequiredCreateTaskViewOps {

    private lateinit var viewListener : View

    @Inject
    lateinit var presenter : ProvidedCreateTaskListPresenterOps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_task)

        setComponent()
        setToolbar()
        setViewPager()
    }

    private fun setComponent() {
        Reminder.get(this).component
                .getCreateTaskComponent(CreateTaskActivityModule(this))
                .inject(this)
    }

    private fun setToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.activity_create_task_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setViewPager() {
        create_task_pager.adapter = CreateTaskPager(supportFragmentManager)
    }

    private inner class CreateTaskPager(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) = presenter.getItem(position)

        override fun getCount() = presenter.getCount()

    }

}
