package fr.jeantuffier.reminder.common.services

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

/**
 * Created by jean on 14.03.2017.
 */
interface ServiceConnectionObserver {
    var bound : Boolean

    fun getServiceConnection() = object : ServiceConnection {

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

    fun onObserverConnected(className: ComponentName, service: IBinder)
    fun onObserverDisconnected(className: ComponentName)
}