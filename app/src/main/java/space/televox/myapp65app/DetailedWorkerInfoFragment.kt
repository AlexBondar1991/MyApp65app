package space.televox.myapp65app

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DetailedWorkerInfoFragment : Fragment(R.layout.fragment_detailed_worker_info) {

    private lateinit var nameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var birthdayTextView: TextView
    private lateinit var specialtyTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var avatar: ImageView
    private lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextView = view.findViewById(R.id.workerItemNameTextView)
        lastNameTextView = view.findViewById(R.id.workerItemLastNameTextView)
        birthdayTextView = view.findViewById(R.id.workerBirthdayTextView)
        specialtyTextView = view.findViewById(R.id.workerSpecialtyTextView)
        ageTextView = view.findViewById(R.id.workerAgeTextView)
        avatar = view.findViewById(R.id.workerAvatarImageView)

        arguments?.getLong(WorkersListFragment.ID_ARG)?.let { workersId ->
            showDetailWorkerInfo(workersId)
        }
    }

    private fun showDetailWorkerInfo(workerId: Long) {
        val workerInfo = (requireActivity().application as MyApp).dataBase.workerDao()
            .getWorkerWithSpecialities(workerId)
        nameTextView.text = workerInfo.worker.name
        lastNameTextView.text = workerInfo.worker.lName
        birthdayTextView.text = workerInfo.worker.birthday
        specialtyTextView.text = workerInfo.specialities.joinToString { it.name }
        Picasso.get()
            .load(workerInfo.worker.avatarUrl)
            .placeholder(R.drawable.ic_noimage)
            .error(R.drawable.ic_noimage)
            .into(avatar)
    }

    companion object {
        private const val ID_ARG = "id"
        fun newInstance(workerId: Long): DetailedWorkerInfoFragment {
            val bundle = bundleOf(ID_ARG to workerId)
            return DetailedWorkerInfoFragment().apply {
                arguments = bundle
            }
        }
    }
}