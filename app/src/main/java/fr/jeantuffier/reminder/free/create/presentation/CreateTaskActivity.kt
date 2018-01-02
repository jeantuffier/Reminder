package fr.jeantuffier.reminder.free.create.presentation

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.free.common.extension.toDp
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
        setToolbar()
        setViewPager()

        form_create_button.setOnClickListener { createTask() }
    }

    override fun notifyNewItem() {
        setResult(HomeActivity.SUCCESS_CREATE_TASK, intent)
        finish()
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

    private fun createTask() {
        val title = mandatoryFragment.getTitle()
        val delay = mandatoryFragment.getDelay().toInt()
        val frequency = mandatoryFragment.getFrequency()
        val priority = mandatoryFragment.getPriority()
        val time = timeFragment.getTaskTime()
        presenter.createTask(title, delay, frequency, priority, time)
    }

    private inner class CreateTaskPager(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size

    }

}
