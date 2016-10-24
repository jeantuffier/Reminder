package no.hyper.reminder

import no.hyper.memoryorm.Memory
import no.hyper.reminder.common.model.Task
import no.hyper.reminder.list.model.ProvidedTaskModelOps
import no.hyper.reminder.list.presenter.TaskListPresenter
import no.hyper.reminder.list.view.RequiredTaskListViewOps
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

    private lateinit var model : ProvidedTaskModelOps
    private lateinit var view : RequiredTaskListViewOps
    private lateinit var presenter : TaskListPresenter

    @Before
    fun setup() {
        view = Mockito.mock(RequiredTaskListViewOps::class.java)
        model = Mockito.mock(ProvidedTaskModelOps::class.java)
        presenter = TaskListPresenter(view, model)

        mockMethod(model.loadData()).thenReturn(true)

        Memory(RuntimeEnvironment.application).cleanTable(Task::class.java.simpleName, null)
    }

}