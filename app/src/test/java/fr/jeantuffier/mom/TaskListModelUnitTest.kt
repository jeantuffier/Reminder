package fr.jeantuffier.mom

import io.kotlintest.specs.FunSpec
import no.hyper.memoryorm.Memory
import fr.jeantuffier.mom.common.model.Task
import fr.jeantuffier.mom.display.model.ProvidedDisplayTaskModelOps
import fr.jeantuffier.mom.display.model.DisplayTaskModel
import fr.jeantuffier.mom.display.presenter.RequiredDisplayTaskPresenterOps
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

    private lateinit var model : ProvidedDisplayTaskModelOps

    @Before
    fun setup() {
        val presenter = Mockito.mock(RequiredDisplayTaskPresenterOps::class.java)
        model = DisplayTaskModel(presenter)
        Memory(RuntimeEnvironment.application).emptyTable(Task::class.java.simpleName, null)
    }

    @Test
    private fun loadData() {
        /*val timer = Timer(Timer.Frequency.HOURS, 1, "", "", Timer.Alarm.NOTIFICATION)
        model.saveTask(Task("task 1", Priority.LOW, "01/01/2016", timer))
        model.saveTask(Task("task 2", Priority.LOW, "01/01/2016", timer))
        model.saveTask(Task("task 3", Priority.LOW, "01/01/2016", timer))

        model.loadData()

        test("tasks number should be equal to 5") {
            model.getTaskCount() shouldBe 5
        }*/
    }

    @Test
    fun saveTask() {
        test("task id should be > to 0") {
            (model.getTaskCount() > 0) shouldBe true
        }
    }


    @Test
    fun getTask() {
        /*val timer = Timer(Timer.Frequency.HOURS, 1, "", "", Timer.Alarm.NOTIFICATION)
        model.saveTask(Task("task 1", Priority.LOW, "01/01/2016", timer))
        val task = model.getTask(0)
        test("task should be the same > to 0") {
            (task != null) shouldBe true
            task?.getName() shouldBe "task 1"
            task?.getPriority() shouldBe Priority.LOW
        }*/
    }

}