package no.hyper.reminder.create.presenter.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Created by jean on 21.11.2016.
 */
class DisplayNotificationService : IntentService("DisplayNotificationService") {

    override fun onHandleIntent(intent: Intent?) {
        Log.d("DNService", "onHandleIntent")
    }
}