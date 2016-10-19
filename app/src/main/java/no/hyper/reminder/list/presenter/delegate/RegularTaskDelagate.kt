package no.hyper.reminder.list.presenter.delegate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import no.hyper.reminder.common.model.regular.RegularTaskViewHolder

/**
 * Created by jean on 14.10.2016.
 */
class RegularTaskDelagate {

    fun createViewHolder(parent: ViewGroup?, viewType: Int): RegularTaskViewHolder {
        val layout = LayoutInflater.from(parent?.context).inflate(viewType, parent, false)
        return RegularTaskViewHolder(layout)
    }

    fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

}