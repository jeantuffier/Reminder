package fr.jeantuffier.reminder.free.create.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ArrayAdapter
import dagger.android.AndroidInjection
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.free.common.utils.SimpleSeekBarChangeListener
import fr.jeantuffier.reminder.free.create.view.fragment.picker.CreateTaskTimePicker
import fr.jeantuffier.reminder.free.create.view.DateTimePickerListener
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity
import kotlinx.android.synthetic.main.create_task_activity.*
import javax.inject.Inject

private const val FROM = "CreateTaskActivity.FROM"
private const val TO = "CreateTaskActivity.TO"

class CreateTaskActivity : AppCompatActivity(), CreateTaskContract.View, DateTimePickerListener {

    @Inject
    lateinit var presenter: CreateTaskContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.create_task_activity)
        setToolbar()
        setFrequencySpinner()
        setPriorityBar()
        setDatePickers()

        form_create_button.setOnClickListener { createTask() }
    }

    override fun notifyNewItem() {
        setResult(HomeActivity.SUCCESS_CREATE_TASK, intent)
        finish()
    }

    override fun onTimePicked(time: String, tag: String) {
        if (tag == FROM) {
            between_input_field.setText(time)
        } else {
            and_input_field.setText(time)
        }
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.activity_create_task_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setFrequencySpinner() {
        val frequencies = arrayListOf(getString(R.string.create_task_frequency_hours),
                getString(R.string.create_task_frequency_minutes))
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, frequencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        task_frequency.adapter = adapter
        task_frequency.setSelection(0)
    }

    private fun setPriorityBar() {
        val priority = getString(R.string.create_task_priority, getString(R.string.create_task_priority_middle).toLowerCase())
        task_priority_progress.text = priority
        task_priority.setOnSeekBarChangeListener(SimpleSeekBarChangeListener { progress ->
            setSeekBarLabel(progress)
        })
    }

    private fun setSeekBarLabel(progress: Int) {
        task_priority_progress.text = when (progress) {
            0 -> getSeekBarFormat(R.string.create_task_priority_low)
            1 -> getSeekBarFormat(R.string.create_task_priority_middle)
            else -> getSeekBarFormat(R.string.create_task_priority_high)
        }
    }

    private fun getSeekBarFormat(resourceId: Int) =
            getString(R.string.create_task_priority, getString(resourceId).toLowerCase())

    private fun setDatePickers() {
        setTimePickerListener(between_input_field, FROM)
        setTimePickerListener(and_input_field, TO)
    }

    private fun setTimePickerListener(view: View, tag: String) {
        view.setOnFocusChangeListener { view, focused ->
            if (focused) {
                showTimePickerDialog(tag)
            }
        }
        view.setOnClickListener {
            showTimePickerDialog(tag)
        }
    }

    private fun showTimePickerDialog(tag: String) {
        val newFragment = CreateTaskTimePicker()
        newFragment.listener = this
        newFragment.show(supportFragmentManager, tag)
    }

    private fun createTask() {
        val title = title_input_field.text.toString()
        val delay = delay_input_field.text.toString().toInt()
        val frequency = task_frequency.selectedItem as String
        val priority = task_priority.progress
        val time = arrayOf(between_input_field.text.toString(), and_input_field.text.toString())
        presenter.createTask(title, delay, frequency, priority, time)
    }

}
