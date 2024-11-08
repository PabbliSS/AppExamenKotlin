package org.eurekamps.appexamenkotlin.fragmentsHome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.eurekamps.appexamenkotlin.R
import org.eurekamps.appexamenkotlin.adapters.RvListTasksAdapter
import org.eurekamps.appexamenkotlin.viewmodelsMain.ListTasksViewModel
import org.eurekamps.appexamenkotlin.fbClass.FbTask

class ListTasksFragment : Fragment() {

    private lateinit var btnCrearTarea: Button
    private lateinit var rvListTareas: RecyclerView
    private lateinit var taskViewModel: ListTasksViewModel
    private lateinit var adapter: RvListTasksAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.fragment_list_tasks, container, false)
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCrearTarea = view.findViewById(R.id.btnCrearTarea)
        rvListTareas= view.findViewById(R.id.rvListTareas)

        // Inicializar el ViewModel
        taskViewModel = ViewModelProvider(this).get(ListTasksViewModel::class.java)

        // Configurar RecyclerView y adaptador
        adapter = RvListTasksAdapter(emptyList()) { task ->
            // Aquí manejas el clic en la tarea (por ejemplo, navegar a un detalle de tarea)
            Log.v("LIST TASKS", "Clic en tarea: ${task.titulo}")
        }



        rvListTareas.layoutManager = LinearLayoutManager(context)
        rvListTareas.adapter = adapter

        // Observar los cambios en la lista de tareas
        taskViewModel.taskList.observe(viewLifecycleOwner, { tasks ->
            // Actualizar la lista de tareas en el adaptador
            adapter.updateTasks(tasks)
        })


        // Configurar el clic en el botón "Crear Tarea"
        btnCrearTarea.setOnClickListener {
            findNavController().navigate(R.id.action_listTasksFragment_to_taskCreatorFragment2)
            Log.v("LIST TASKS", "SE HA PULSADO EL BOTON DE CREAR TAREA")
        }
    }
}
