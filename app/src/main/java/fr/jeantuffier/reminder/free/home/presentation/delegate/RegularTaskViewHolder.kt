package fr.jeantuffier.reminder.free.home.presentation.delegate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import fr.jeantuffier.reminder.R

class RegularTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val shape = itemView.findViewById(R.id.task_priority) as ImageView
    val title = itemView.findViewById(R.id.task_title) as TextView
    val frequency = itemView.findViewById(R.id.task_frequency) as TextView
    val between = itemView.findViewById(R.id.task_between) as TextView
    val and = itemView.findViewById(R.id.task_and) as TextView

}