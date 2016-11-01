package no.hyper.reminder.create.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_creator_task.*
import no.hyper.reminder.R

class CreateTaskActivity : AppCompatActivity(), DateTimePickerListener {

    private lateinit var viewListener : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_task)

        setToolbar()
        setListener(form_start?.editText)
        setListener(form_end?.editText)

    }

    override fun onDatePicked(date: String) {
        (viewListener as EditText).setText(date)
        showTimePickerDialog()
    }

    override fun onTimePicked(time: String) {
        val date = (viewListener as EditText).text
        date.append(" $time")
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(viewListener.windowToken, 0)
    }

    private fun setToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.activity_create_task_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDatePickerDialog() {
        val newFragment = DialogDatePicker()
        newFragment.show(fragmentManager, "datePicker")
    }

    private fun showTimePickerDialog() {
        val newFragment = DialogTimePicker()
        newFragment.show(fragmentManager, "timePicker")
    }

    private fun setListener(view: View?) {
        view?.setOnFocusChangeListener { view, focused ->
            if (focused) {
                viewListener = view
                showDatePickerDialog()
            }
        }
        view?.setOnClickListener {
            viewListener = view
            showDatePickerDialog()
        }
    }

}
