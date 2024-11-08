package org.eurekamps.appexamenkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.eurekamps.appexamenkotlin.R
import org.eurekamps.appexamenkotlin.fbClass.FbTask


class RvListTasksAdapter(
    private var tasksList: List<FbTask>,
    private val onClick: (FbTask) -> Unit
) : RecyclerView.Adapter<RvListTasksAdapter.TaskViewHolder>() {




    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloTextView: TextView = view.findViewById(R.id.tvTituloTarea)
        val descripcionTextView: TextView = view.findViewById(R.id.tvDescripcionTarea)
        val fechaLimiteTextView: TextView = view.findViewById(R.id.tvFechaLimite)


        fun bind(task: FbTask, onClick: (FbTask) -> Unit) {
            tituloTextView.text = task.titulo ?: "Sin título"
            descripcionTextView.text = task.descripcion ?: "Sin descripción"
            fechaLimiteTextView.text = task.fechaLimite ?: "Fecha límite no establecida"


            itemView.setOnClickListener {
                onClick(task) // Llama al listener de clic con la tarea actual
            }
        }
    }



    // Create new views (called by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false) // Asegúrate de tener un layout `item_task.xml`
        return TaskViewHolder(view)
    }




    // Replace the contents of a view (called by the layout manager)
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasksList[position]
        holder.bind(task, onClick) // Pasamos la tarea y el listener al bind
    }




    // Return the size of the list
    override fun getItemCount(): Int {
        return tasksList.size
    }



    // Actualizar la lista de tareas
    fun updateTasks(newTasks: List<FbTask>) {
        tasksList = newTasks
        notifyDataSetChanged()
    }
}
