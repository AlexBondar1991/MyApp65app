package space.televox.myapp65app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.televox.myapp65app.data.Worker


class WorkerListAdapter(
    private val listener: (worker: Worker) -> Unit
) : RecyclerView.Adapter<WorkerListAdapter.WorkerViewHolder>() {

    var workersList: List<Worker> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_worker, parent, false)
        return WorkerViewHolder(v)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        holder.bind(workersList[position], listener)
    }

    override fun getItemCount(): Int {
        return workersList.size
    }

    class WorkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tvName)

        fun bind(
            worker: Worker,
            listener: (worker: Worker) -> Unit

        ) {
            tvName.text = worker.name
            itemView.setOnClickListener {
                listener.invoke(worker)
            }
        }
    }
}