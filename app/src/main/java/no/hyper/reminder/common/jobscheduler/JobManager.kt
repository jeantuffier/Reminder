package no.hyper.reminder.common.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.PersistableBundle
import no.hyper.reminder.common.extension.editPreferences
import no.hyper.reminder.common.extension.withExtras
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.timer.Timer
import no.hyper.reminder.create.presenter.service.DisplayNotificationService
import java.util.concurrent.TimeUnit

/**
 * Created by jean on 24.11.2016.
 */
object JobManager {

    private val LIMIT_JOB = 50
    private val jobs = mutableMapOf<String, Int>()

    private fun getNewJobId() = Array(LIMIT_JOB, { i -> i }).filter { !jobs.containsValue(it) }.first()

    private fun persistJob(context: Context, taskId: String, jobId: Int) {
        context.editPreferences { it.putInt(context.packageName + taskId, jobId) }
    }

    private fun unPersistJob(context: Context, taskId: String) {
        context.editPreferences { it.remove(context.packageName + taskId) }
    }

    fun registerNewJob(context: Context, task: Task) {
        val delay = task.getTimer().delay
        val frequency = task.getTimer().frequency
        val period = if (frequency == Timer.Frequency.HOURS) {
            TimeUnit.HOURS.toMillis(delay.toLong())
        } else {
            TimeUnit.MINUTES.toMillis(delay.toLong())
        }

        val jobId = getNewJobId()
        jobs.put(task.getId(), jobId)

        val componentName = ComponentName(context, DisplayNotificationService::class.java)
        val builder = JobInfo.Builder(jobId, componentName)
        builder.setPeriodic(period)
        builder.setRequiresDeviceIdle(false)
        builder.setRequiresCharging(false)

        val bundle = PersistableBundle().withExtras(Task.TITLE to task.getTitle())
        builder.setExtras(bundle)

        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())
        persistJob(context, task.getId(), jobId)
    }

    fun unregisterJob(context:Context, task: Task) {
        jobs[task.getId()]?.let {
            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(it)
            unPersistJob(context, task.getId())
        }
    }

}