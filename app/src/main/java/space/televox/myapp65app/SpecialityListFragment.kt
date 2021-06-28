package space.televox.myapp65app

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.televox.myapp65app.data.Speciality
import space.televox.myapp65app.data.db.entitis.SpecialityEntity
import space.televox.myapp65app.data.db.entitis.SpecialityWorkerCrossRef
import space.televox.myapp65app.data.db.entitis.WorkerEntity
import space.televox.myapp65app.data.dto.WorkerDto
import space.televox.myapp65app.data.dto.WorkersListDto
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class SpecialityListFragment : Fragment(R.layout.fragment_speciality_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var specialityListAdapter: SpecialityListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_speciality_list)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        specialityListAdapter = SpecialityListAdapter { speciality ->
            (requireActivity() as MainActivity).openWorkersList(speciality.id)
        }
        recyclerView.adapter = specialityListAdapter

        getWorkersList()

    }

    private fun getWorkersList() {
        (requireActivity().application as MyApp).restApi.getWorkers().enqueue(object :
            Callback<WorkersListDto> {

            override fun onResponse(
                call: Call<WorkersListDto>,
                response: Response<WorkersListDto>
            ) {
                response.body()?.response?.let { workers ->
                    handleResult(workers)
                } ?: showError()
            }

            override fun onFailure(call: Call<WorkersListDto>, t: Throwable) {
                showError()
            }
        })
    }

    private fun handleResult(workers: List<WorkerDto>) {

        val specialities = workers
            .flatMap { it.speciality }
            .map { Speciality(it.id, it.name) }
            .distinct()

        specialities.forEach { speciality ->
            val specialityEntity =
                SpecialityEntity(specialityId = speciality.id, name = speciality.name.normalize())
            (requireActivity().application as MyApp).dataBase.specialtyDao()
                .insert(specialityEntity)
        }

        (requireActivity().application as MyApp).dataBase.workerDao().deleteAllWorkers()
        (requireActivity().application as MyApp).dataBase.specialtyDao().deleteAllCrossRef()

        workers.forEach { worker ->
            val workerEntity = WorkerEntity(
                name = worker.firstName.normalize(),
                lName = worker.lastName.normalize(),
                birthday = worker.birthday.orEmpty(),
                avatarUrl = worker.avatarUrl.orEmpty()
            )
            val workerEntityId =
                (requireActivity().application as MyApp).dataBase.workerDao().insert(workerEntity)
            worker.speciality.forEach { speciality ->
                (requireActivity().application as MyApp).dataBase.specialtyDao().insertCrossRef(
                    SpecialityWorkerCrossRef(
                        specialityId = speciality.id,
                        workerId = workerEntityId
                    )
                )
            }
        }
        showSpecialityList(specialities)
    }

    private fun showSpecialityList(specialities: List<Speciality>) {
        specialityListAdapter.specialityList = specialities
        specialityListAdapter.notifyDataSetChanged()
    }

    fun showError() {
        Toast.makeText(requireActivity(), R.string.general_error, Toast.LENGTH_SHORT).show()
    }

    private fun String.normalize(): String {
        return substring(0, 1).toUpperCase() + substring(1).toLowerCase()
    }


}
