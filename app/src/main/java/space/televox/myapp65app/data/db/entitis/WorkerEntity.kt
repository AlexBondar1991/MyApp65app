package space.televox.myapp65app.data.db.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkerEntity(
    @PrimaryKey(autoGenerate = true)
    var workerId: Long = 0,
    var name: String = "",
    var lName: String = "",
    var birthday: String = "",
    var avatarUrl: String = ""
)