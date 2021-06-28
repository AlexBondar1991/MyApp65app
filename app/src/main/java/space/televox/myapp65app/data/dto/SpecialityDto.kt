package space.televox.myapp65app.data.dto

import com.google.gson.annotations.SerializedName

data class SpecialityDto(
    @SerializedName("specialty_id")
    val id: Long,

    @SerializedName("name")
    val name: String
)