package fr.jeantuffier.reminder.common.service

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

/**
 * Created by jean on 14.03.2017.
 */
abstract class ServiceConnectionObserver {
    var bound = false
        get() = field
        set(value) { field = value}

    val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.d("DNS", "service is connected")
            bound = true
            onObserverConnected(className, service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.d("DNS", "service is disconnected")
            bound = false
            onObserverDisconnected(className)
        }

    }

    abstract fun onObserverConnected(className: ComponentName, service: IBinder)
    abstract fun onObserverDisconnected(className: ComponentName)
}