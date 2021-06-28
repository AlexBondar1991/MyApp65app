package space.televox.myapp65app.data.db.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity//(primaryKeys = ["specialityId", "workerId"])
data class SpecialityWorkerCrossRef(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var specialityId: Long = 0,
    var workerId: Long = 0
)