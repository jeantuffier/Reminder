package fr.jeantuffier.reminder.free.common.service

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

abstract class AbstractConnectionObserver {

    var bound = false

    val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.d("ConnectionObserver", "service is connected")
            bound = true
            onObserverConnected(className, service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.d("ConnectionObserver", "service is disconnected")
            bound = false
            onObserverDisconnected(className)
        }

    }

    abstract fun onObserverConnected(className: ComponentName, service: IBinder)
    abstract fun onObserverDisconnected(className: ComponentName)

}