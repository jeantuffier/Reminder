package fr.jeantuffier.reminder.free.common.dao

import android.arch.persistence.room.*
import fr.jeantuffier.reminder.free.common.model.Task
import io.reactivex.Flowable

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): Flowable<List<Task>>

    @Query("SELECT * FROM task WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): Flowable<Task>

    @Insert
    fun insertAll(tasks: Array<Task>)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}
