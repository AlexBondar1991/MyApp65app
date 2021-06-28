package space.televox.myapp65app.retrofit

import retrofit2.Call
import retrofit2.http.GET
import space.televox.myapp65app.data.dto.WorkersListDto

interface RestApi {
    @GET("testTask.json")
    fun getWorkers(): Call<WorkersListDto>
}