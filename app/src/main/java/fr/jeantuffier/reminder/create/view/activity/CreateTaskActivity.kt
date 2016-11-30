package fr.jeantuffier.reminder.create.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.create_task_activity.*
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.common.Reminder
import fr.jeantuffier.reminder.common.extension.getInteger
import fr.jeantuffier.reminder.common.extension.toDp
import fr.jeantuffier.reminder.create.injection.CreateTaskActivityModule
import fr.jeantuffier.reminder.create.presenter.ProvidedCreateTaskPresenterOps
import fr.jeantuffier.reminder.create.view.fragment.CreateTaskFrequencyFragment
import fr.jeantuffier.reminder.create.view.fragment.CreateTaskPriorityFragment
import fr.jeantuffier.reminder.create.view.fragment.CreateTaskTitleFragment
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

        form_create_button.setOnClickListener {
            presenter.createTask()
        }

        setComponent()
        setToolbar()
        setViewPager()
    }

    override fun getTaskTitle() = createTaskTitleFragment.getTitle()

    override fun getTaskFrequencyDelay() = createTaskFrequencyFragment.getFrequencyDelay()

    override fun getTaskFrequencyType() = createTaskFrequencyFragment.getFrequencyType()

    override fun getTaskPriority() = createTaskPriorityFragment.getTaskPriority()

    override fun getResourceString(id: Int) : String = getString(id)

    override fun notifyNewItem() {
        setResult(getInteger(R.integer.result_create_task_success), intent)
        finish()
    }

    override fun error(message: String) {
        Snackbar.make(task_creator_root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun getActivityContext() = this

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
