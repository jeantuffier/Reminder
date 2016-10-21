package no.hyper.reminder

import io.kotlintest.specs.FunSpec
import no.hyper.memoryorm.Memory
import no.hyper.reminder.common.model.Task
import no.hyper.reminder.common.model.regular.RegularTask
import no.hyper.reminder.list.model.ProvidedTaskModelOps
import no.hyper.reminder.list.model.TaskListModel
import no.hyper.reminder.list.presenter.RequiredTaskListPresenterOps
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


/**
 * Created by jean on 21.10.2016.
 */

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(25), manifest = "/src/main/AndroidManifest.xml")
class TaskListModelUnitTest : FunSpec() {

    private lateinit var model : ProvidedTaskModelOps

    @Before
    fun setup() {
        val presenter = Mockito.mock(RequiredTaskListPresenterOps::class.java)
        model = TaskListModel(presenter)
        Memory(RuntimeEnvironment.application).cleanTable(Task::class.java.simpleName, null)
    }

    @Test
    private fun loadData() {
        for (i in 0..5) {
            model.saveTask(RegularTask("task$i", i))
        }

        model.loadData()

        test("tasks number should be equal to 5") {
            model.getTaskCount() shouldBe 5
        }
    }

    @Test
    fun saveTask() {
        test("task id should be > to 0") {
            (model.getTaskCount() > 0) shouldBe true
        }
    }


    @Test
    fun getTask() {
        model.saveTask(RegularTask("task", 1))
        val task = model.getTask(0)
        test("task should be the same > to 0") {
            (task != null) shouldBe true
            task?.getName() shouldBe "task"
            task?.getPriority() shouldBe 1
        }
    }

}