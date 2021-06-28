package space.televox.myapp65app.data.db

import androidx.room.*
import space.televox.myapp65app.data.db.entitis.SpecialityEntity
import space.televox.myapp65app.data.db.entitis.SpecialityWithWorkers
import space.televox.myapp65app.data.db.entitis.WorkerEntity
import space.televox.myapp65app.data.db.entitis.WorkerWithSpecialities

@Dao
interface WorkerDao {
    @Query("SELECT * FROM WorkerEntity WHERE workerId = :workerId")
    fun getById(workerId: Long): WorkerEntity

    @Insert
    fun insert(worker: WorkerEntity): Long

    @Query("DELETE FROM WorkerEntity")
    fun deleteAllWorkers()

    @Transaction
    @Query("SELECT * FROM WorkerEntity WHERE workerId = :workerId")
    fun getWorkerWithSpecialities(workerId: Long): WorkerWithSpecialities

}