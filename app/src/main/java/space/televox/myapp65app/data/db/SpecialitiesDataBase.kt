package space.televox.myapp65app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import space.televox.myapp65app.data.db.entitis.SpecialityEntity
import space.televox.myapp65app.data.db.entitis.SpecialityWorkerCrossRef
import space.televox.myapp65app.data.db.entitis.WorkerEntity

@Database(
    entities = [SpecialityEntity::class, WorkerEntity::class, SpecialityWorkerCrossRef::class],
    version = 1
)
abstract class SpecialitiesDataBase : RoomDatabase() {
    abstract fun specialtyDao(): SpecialityDao
    abstract fun workerDao(): WorkerDao
}