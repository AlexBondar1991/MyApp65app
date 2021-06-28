package space.televox.myapp65app.data.db.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SpecialityEntity(
    @PrimaryKey
    var specialityId: Long = 0,
    var name: String = ""
)