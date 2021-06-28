package space.televox.myapp65app

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.televox.myapp65app.data.Worker

class WorkersListFragment : Fragment(R.layout.fragment_workers_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workerListAdapter: WorkerListAdapter
    private lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.recycler_workers)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        workerListAdapter = WorkerListAdapter { worker ->
            (requireActivity() as MainActivity).openDetailedInfo(worker)
        }
        recyclerView.adapter = workerListAdapter

        toolbar = view.findViewById(R.id.myToolbarWorkersList)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        arguments?.getLong(ID_ARG)?.let { specialityId ->
            showWorkers(specialityId)
        }
    }

    private fun showWorkers(specialityId: Long) {
        val workers = (requireActivity().application as MyApp).dataBase.specialtyDao()
            .getSpecialityWithWorkers(specialityId).workers.map {
                Worker(
                    workerId = it.workerId,
                    name = it.name,
                    lName = it.lName,
                    birthday = it.birthday,
                    avatarUrl = it.avatarUrl
                )
            }
        workerListAdapter.workersList = workers
        workerListAdapter.notifyDataSetChanged()
    }


    companion object {
        const val ID_ARG = "id"
        fun newInstance(specialityId: Long): WorkersListFragment {
            val bundle = bundleOf(ID_ARG to specialityId)
            return WorkersListFragment().apply {
                arguments = bundle
            }
        }
    }
}