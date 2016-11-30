package fr.jeantuffier.reminder.display.model.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import fr.jeantuffier.reminder.R

/**
 * Created by jean on 14.10.2016.
 */
class RegularTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val icon : ImageView
    val title : TextView
    val frequency : TextView

    init {
        title = itemView.findViewById(R.id.task_title) as TextView
        icon = itemView.findViewById(R.id.ic_priority) as ImageView
        frequency = itemView.findViewById(R.id.task_frequency) as TextView
    }

}