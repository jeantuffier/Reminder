package fr.jeantuffier.reminder

import no.hyper.memoryorm.Memory
import fr.jeantuffier.reminder.common.model.Task
import fr.jeantuffier.reminder.display.model.ProvidedDisplayTaskModelOps
import fr.jeantuffier.reminder.display.presenter.DisplayTaskPresenter
import fr.jeantuffier.reminder.display.view.RequiredDisplayTaskViewOps
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
class TaskListPresenterUnitTest {

    private lateinit var model : ProvidedDisplayTaskModelOps
    private lateinit var view : RequiredDisplayTaskViewOps
    private lateinit var presenter : DisplayTaskPresenter

    @Before
    fun setup() {
        view = Mockito.mock(RequiredDisplayTaskViewOps::class.java)
        model = Mockito.mock(ProvidedDisplayTaskModelOps::class.java)
        presenter = DisplayTaskPresenter(view)

        //mockMethod(model.loadData()).thenReturn(true)

        Memory(RuntimeEnvironment.application).emptyTable(Task::class.java.simpleName, null)
    }

}