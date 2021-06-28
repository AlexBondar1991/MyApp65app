package space.televox.myapp65app

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.televox.myapp65app.data.db.SpecialitiesDataBase
import space.televox.myapp65app.retrofit.RestApi


class MyApp : Application() {

    lateinit var restApi : RestApi
    lateinit var dataBase : SpecialitiesDataBase
    override fun onCreate() {
        super.onCreate()

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gitlab.65apps.com/65gb/static/raw/master/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        restApi = retrofit.create(RestApi::class.java)

        dataBase = Room.databaseBuilder(this, SpecialitiesDataBase::class.java, "DataBase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}