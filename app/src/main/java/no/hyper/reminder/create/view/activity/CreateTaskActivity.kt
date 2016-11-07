package no.hyper.reminder.create.view.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.create_task_activity.*
import no.hyper.reminder.R
import no.hyper.reminder.common.Reminder
import no.hyper.reminder.common.extension.toDp
import no.hyper.reminder.create.injection.CreateTaskActivityModule
import no.hyper.reminder.create.presenter.ProvidedCreateTaskPresenterOps
import no.hyper.reminder.create.view.fragment.CreateTaskFrequencyFragment
import no.hyper.reminder.create.view.fragment.CreateTaskPriorityFragment
import no.hyper.reminder.create.view.fragment.CreateTaskTitleFragment
import javax.inject.Inject

class CreateTaskActivity : AppCompatActivity(), RequiredCreateTaskViewOps {

    private val createTaskTitleFragment = CreateTaskTitleFragment()
    private val createTaskFrequencyFragment = CreateTaskFrequencyFragment()
    private val createTaskPriorityFragment = CreateTaskPriorityFragment()

    @Inject
    lateinit var presenter : ProvidedCreateTaskPresenterOps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_task_activity)

        setComponent()
        setToolbar()
        setViewPager()
    }

    override fun getTaskTitle() = createTaskTitleFragment.getTitle()

    override fun getTaskFrequencyDelay() = createTaskFrequencyFragment.getFrequencyDelay()

    override fun getTaskFrequencyType() = createTaskFrequencyFragment.getFrequencyType()

    override fun getTaskPriority() = createTaskPriorityFragment.getTaskPriority()

    override fun getResourceString(id: Int) : String = getString(id)

    override fun error(message: String) {
        Snackbar.make(task_creator_root, message, Snackbar.LENGTH_SHORT).show()
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
        create_task_pager.pageMargin = 16.toDp(this)
    }

    private inner class CreateTaskPager(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) : Fragment {
            return when (position) {
                0 -> createTaskTitleFragment
                1 -> createTaskFrequencyFragment
                else -> createTaskPriorityFragment
            }
        }

        override fun getCount() = 3

    }

}
