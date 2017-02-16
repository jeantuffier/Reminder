package fr.jeantuffier.reminder.common.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.PersistableBundle
import fr.jeantuffier.reminder.common.extension.editPreferences
import fr.jeantuffier.reminder.common.extension.readPreference
import fr.jeantuffier.reminder.common.extension.withExtras
import fr.jeantuffier.reminder.common.model.Task
import java.util.concurrent.TimeUnit

/**
 * Created by jean on 24.11.2016.
 */
object JobManager {

    fun registerNewJob(context: Context, task: Task) {
        val period = if (task.frequency == Task.HOURS) {
            TimeUnit.HOURS.toMillis(task.delay.toLong())
        } else {
            TimeUnit.MINUTES.toMillis(task.delay.toLong())
        }

        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val jobId = getNewJobId(jobScheduler)

        val componentName = ComponentName(context, DisplayNotificationService::class.java)
        val builder = JobInfo.Builder(jobId, componentName)
        builder.setPeriodic(period)
        builder.setRequiresDeviceIdle(false)
        builder.setRequiresCharging(false)

        val bundle = PersistableBundle().withExtras(Task.ID to task.id, Task.TITLE to task.title,
                Task.FROM to task.fromTime, Task.TO to task.toTime)
        builder.setExtras(bundle)

        persistJob(context, task.id, jobId)
        jobScheduler.schedule(builder.build())
    }

    fun unregisterJob(context: Context, taskId: String?) {
        val jobId = context.readPreference { it.getInt(context.packageName + taskId, -1) }
        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        if (jobScheduler.allPendingJobs.filter { it.id == jobId  }.isNotEmpty()) {
            unPersistJob(context, taskId)
            jobScheduler.cancel(jobId)
        }
    }

    fun isRegistered(context: Context, taskId: String?) : Boolean {
        val jobId = context.readPreference { it.getInt(context.packageName + taskId, -1) }
        return jobId != -1
    }

    private fun getNewJobId(jobScheduler: JobScheduler) : Int {
        val listJobInfo = jobScheduler.allPendingJobs
        if (listJobInfo.isEmpty()) return 0

        val jobInfo = listJobInfo.maxBy { it.id }
        return (jobInfo?.id  ?: -1) + 1
    }

    private fun persistJob(context: Context, taskId: String, jobId: Int) {
        context.editPreferences { it.putInt(context.packageName + taskId, jobId) }
    }

    private fun unPersistJob(context: Context, taskId: String?) {
        context.editPreferences { it.remove(context.packageName + taskId) }
    }

}