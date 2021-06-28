package space.televox.myapp65app.data.dto

import com.google.gson.annotations.SerializedName

data class WorkerDto(
    @SerializedName("f_name")
    val firstName: String,
    @SerializedName("l_name")
    val lastName: String,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("avatr_url")
    val avatarUrl: String?,
    @SerializedName("specialty")
    val speciality: List<SpecialityDto>
)