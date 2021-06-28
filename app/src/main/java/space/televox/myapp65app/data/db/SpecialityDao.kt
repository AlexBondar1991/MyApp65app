package space.televox.myapp65app.data.db

import androidx.room.*
import space.televox.myapp65app.data.db.entitis.SpecialityEntity
import space.televox.myapp65app.data.db.entitis.SpecialityWithWorkers
import space.televox.myapp65app.data.db.entitis.SpecialityWorkerCrossRef

@Dao
interface SpecialityDao {
    @Query("SELECT * FROM SpecialityEntity WHERE specialityId = :specialityId")
    fun getById(specialityId: Long): SpecialityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(speciality: SpecialityEntity)

    @Transaction
    @Query("SELECT * FROM SpecialityEntity WHERE specialityId = :specialityId")
    fun getSpecialityWithWorkers(specialityId: Long): SpecialityWithWorkers

    @Insert
    fun insertCrossRef(crossRef: SpecialityWorkerCrossRef)

    @Query("DELETE FROM SpecialityWorkerCrossRef")
    fun deleteAllCrossRef()
}