package fr.jeantuffier.reminder.free.create.presentation

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.free.common.Reminder
import fr.jeantuffier.reminder.free.common.extension.toDp
import fr.jeantuffier.reminder.free.create.injection.CreateTaskActivityModule
import fr.jeantuffier.reminder.free.create.view.CreateTaskMandatoryFragment
import fr.jeantuffier.reminder.free.create.view.CreateTaskTimeFragment
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity
import kotlinx.android.synthetic.main.create_task_activity.*
import javax.inject.Inject

class CreateTaskActivity : AppCompatActivity(), CreateTaskContract.View {

    private val mandatoryFragment = CreateTaskMandatoryFragment()
    private val timeFragment = CreateTaskTimeFragment()
    private val fragments = arrayOf(mandatoryFragment, timeFragment)

    @Inject
    lateinit var presenter: CreateTaskContract.Presenter

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

    override fun getTaskTitle() = mandatoryFragment.getTitle()

    override fun getDelay() = mandatoryFragment.getDelay()

    override fun getFrequency() = mandatoryFragment.getFrequency()

    override fun getPriority() = mandatoryFragment.getPriority()

    override fun getTaskTime() = timeFragment.getTaskTime()

    override fun getResourceString(id: Int): String = getString(id)

    override fun notifyNewItem() {
        setResult(HomeActivity.SUCCESS_CREATE_TASK, intent)
        finish()
    }

    override fun error(message: String) {
        Snackbar.make(task_creator_root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun getActivityContext() = this

    private fun setComponent() {
        Reminder.instance.component
                .getCreateTaskComponent(CreateTaskActivityModule(this))
                .inject(this)
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.activity_create_task_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setViewPager() {
        create_task_pager.adapter = CreateTaskPager(supportFragmentManager)
        create_task_pager.pageMargin = 16.toDp(this)
    }

    private inner class CreateTaskPager(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size

    }

}
