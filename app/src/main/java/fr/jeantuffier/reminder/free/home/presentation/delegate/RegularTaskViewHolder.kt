package fr.jeantuffier.reminder.free.home.presentation.delegate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import fr.jeantuffier.reminder.R

class RegularTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val shape = itemView.findViewById(R.id.priority) as ImageView
    val title = itemView.findViewById(R.id.title) as TextView
    val frequency = itemView.findViewById(R.id.frequency) as TextView
    val between = itemView.findViewById(R.id.between) as TextView
    val and = itemView.findViewById(R.id.and) as TextView

    private val more = itemView.findViewById(R.id.more) as ImageView
    private val popupMenu = PopupMenu(itemView.context, more)

    init {
        popupMenu.menu.add(0,0,0, itemView.context.getString(R.string.task_delete))
        more.setOnClickListener { ItemClickDispatcher.publishSubject?.onNext(itemView.tag as Int) }
    }
}