package space.televox.myapp65app


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class DetailedWorkerInfoFragment :
    androidx.fragment.app.Fragment(R.layout.fragment_detailed_worker_info) {

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

        toolbar = view.findViewById(R.id.myToolbarWorkerDetail)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        arguments?.getLong(WorkersListFragment.ID_ARG)?.let { workersId ->
            showDetailWorkerInfo(workersId)
        }
    }

    private fun showDetailWorkerInfo(workerId: Long) {
        val workerInfo = (requireActivity().application as MyApp).dataBase.workerDao()
            .getWorkerWithSpecialities(workerId)
        nameTextView.text = workerInfo.worker.name
        lastNameTextView.text = workerInfo.worker.lName
        birthdayTextView.text = workerInfo.worker.birthday.formatDate(workerInfo.worker.birthday)
        ageTextView.text =
            calculateAge(workerInfo.worker.birthday.formatDate(workerInfo.worker.birthday))
        specialtyTextView.text = workerInfo.specialities.joinToString { it.name }
        if (workerInfo.worker.avatarUrl.isNullOrEmpty()) {
            Picasso.get()
                .load(R.drawable.ic_noimage)
                .placeholder(R.drawable.ic_noimage)
                .error(R.drawable.ic_noimage)
                .into(avatar)
        } else {
            Picasso.get()
                .load(workerInfo.worker.avatarUrl)
                .placeholder(R.drawable.ic_noimage)
                .error(R.drawable.ic_noimage)
                .into(avatar)
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun String.formatDate(birthday: String): String {
        var birthday = birthday.replace('-', '.')
        val correctPattern: String = "dd.MM.yyyy"
        val uncorrectPattern: String = "yyyy.MM.dd"
        val simpleDateFormat = SimpleDateFormat(uncorrectPattern)
        simpleDateFormat.isLenient = false

        try {
            if (simpleDateFormat.parse(birthday) != null) {
                val date = simpleDateFormat.parse(birthday)
                simpleDateFormat.applyPattern(correctPattern)
                birthday = simpleDateFormat.format(date)
            }

        } catch (exc: Exception) {
            try {
                simpleDateFormat.applyPattern(correctPattern)
                simpleDateFormat.parse(birthday)
            } catch (e: Exception) {
                birthday = "-"
            }
        }
        calculateAge(birthday)
        return birthday

    }

    @SuppressLint("SimpleDateFormat")
    private fun calculateAge(birthday: String): String {
        var age: Int?
        val dateFormat = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        val year = SimpleDateFormat("yyyy")
        val month = SimpleDateFormat("MM")

        try {
            val currentDate = Date()
            val birthday = simpleDateFormat.parse(birthday)
            age =
                Integer.valueOf(year.format(currentDate)) - Integer.valueOf(year.format(birthday))
            if (Integer.valueOf(month.format(currentDate)) < Integer.valueOf(
                    month.format(
                        birthday
                    )
                )
            ) {
                age -= 1
            }
            if (Integer.valueOf(month.format(currentDate)) == Integer.valueOf(
                    month.format(
                        birthday
                    )
                )
            ) {
                val day = SimpleDateFormat("dd")
                if (Integer.valueOf(day.format(currentDate)) < Integer.valueOf(
                        day.format(
                            birthday
                        )
                    )
                ) {
                    age -= 1
                }
            }
        } catch (exc: Exception) {
            age = null
        }
        return age.toString()
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