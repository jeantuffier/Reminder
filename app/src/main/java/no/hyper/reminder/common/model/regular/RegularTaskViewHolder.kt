package no.hyper.reminder.common.model.regular

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import no.hyper.reminder.R

/**
 * Created by jean on 14.10.2016.
 */
class RegularTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title : TextView

    init {
        title = itemView.findViewById(R.id.task_title) as TextView
    }

}