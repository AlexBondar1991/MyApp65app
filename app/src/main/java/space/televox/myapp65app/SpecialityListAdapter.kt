package space.televox.myapp65app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.televox.myapp65app.data.Speciality

class SpecialityListAdapter(
    private val listener: (specialityType: Speciality) -> Unit
) : RecyclerView.Adapter<SpecialityListAdapter.SpecialityViewHolder>() {

    var specialityList: List<Speciality> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_speciality, parent, false)
        return SpecialityViewHolder(v)
    }

    override fun onBindViewHolder(holder: SpecialityViewHolder, position: Int) {
        holder.bind(specialityList[position], listener)
    }

    override fun getItemCount(): Int {
        return specialityList.size
    }

    class SpecialityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSpeciality: TextView = itemView.findViewById(R.id.tvSpecialityType)
        fun bind(
            specialityType: Speciality,
            listener: (specialityType: Speciality) -> Unit

        ) {
            tvSpeciality.text = specialityType.name
            itemView.setOnClickListener {
                listener.invoke(specialityType)
            }
        }

    }
}