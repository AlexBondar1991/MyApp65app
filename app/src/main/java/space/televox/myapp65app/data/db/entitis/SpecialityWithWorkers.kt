package space.televox.myapp65app.data.db.entitis

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SpecialityWithWorkers(
    @Embedded val speciality: SpecialityEntity,
    @Relation(
        parentColumn = "specialityId",
        entityColumn = "workerId",
        associateBy = Junction(SpecialityWorkerCrossRef::class)
    )
    var workers: List<WorkerEntity>
)
