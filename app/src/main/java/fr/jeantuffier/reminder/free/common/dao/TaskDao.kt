package fr.jeantuffier.reminder.free.common.dao

import android.arch.persistence.room.*
import fr.jeantuffier.reminder.free.common.model.Task
import io.reactivex.Single

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): Single<List<Task>>

    @Query("SELECT * FROM task WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): Single<Task>

    @Query("DELETE FROM task WHERE id = :id")
    fun deleteById(id: Int)

    @Insert
    fun insert(task: Task): Long

    @Insert
    fun insertAll(tasks: Array<Task>)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}
