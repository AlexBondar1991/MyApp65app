package space.televox.myapp65app.data.db.entitis

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class WorkerWithSpecialities(
    @Embedded val worker: WorkerEntity,
    @Relation(
        parentColumn = "workerId",
        entityColumn = "specialityId",
        associateBy = Junction(SpecialityWorkerCrossRef::class)
    )
    var specialities: List<SpecialityEntity>
)
