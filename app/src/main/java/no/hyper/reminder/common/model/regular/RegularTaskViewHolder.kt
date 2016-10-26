package no.hyper.reminder.common.model.regular

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import no.hyper.reminder.R

/**
 * Created by jean on 14.10.2016.
 */
class RegularTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val backgroundIcon : FrameLayout
    val icon : ImageView
    val title : TextView

    init {
        title = itemView.findViewById(R.id.task_title) as TextView
        backgroundIcon = itemView.findViewById(R.id.root_priority) as FrameLayout
        icon = itemView.findViewById(R.id.ic_priority) as ImageView
    }

}