package space.televox.myapp65app.data.dto

import com.google.gson.annotations.SerializedName

data class WorkersListDto(
    @SerializedName("response")
    val response: List<WorkerDto>
)