package no.hyper.reminder.common.model.task.regular

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import no.hyper.reminder.R

/**
 * Created by jean on 14.10.2016.
 */
class RegularTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val icon : ImageView
    val title : TextView
    val frequency : TextView
    val switch : Switch

    init {
        title = itemView.findViewById(R.id.task_title) as TextView
        icon = itemView.findViewById(R.id.ic_priority) as ImageView
        frequency = itemView.findViewById(R.id.task_frequency) as TextView
        switch = itemView.findViewById(R.id.task_activate_switch) as Switch
    }

}