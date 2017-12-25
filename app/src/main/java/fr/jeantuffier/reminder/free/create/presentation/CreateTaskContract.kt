package fr.jeantuffier.reminder.free.create.presentation

class CreateTaskContract {

    interface View {
        fun notifyNewItem()
        fun error(message: String)
    }

    interface Presenter {
        fun createTask(title: String, delay: Int, frequency: String, priorityForm: Int, time: Array<String>)
    }
}