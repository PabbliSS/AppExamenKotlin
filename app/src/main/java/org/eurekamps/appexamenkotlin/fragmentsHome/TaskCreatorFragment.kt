package org.eurekamps.appexamenkotlin.fragmentsHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.eurekamps.appexamenkotlin.R
import org.eurekamps.appexamenkotlin.fbClass.FbTask
import org.eurekamps.appexamenkotlin.viewmodelsMain.TaskCreatorViewModel

class TaskCreatorFragment : Fragment() {


    lateinit var taskCreatorViewModel: TaskCreatorViewModel  // Usamos TaskCreatorViewModel

    lateinit var edtTitulo: EditText
    lateinit var edtDescripcion: EditText
    lateinit var edtFechaLimite: EditText
    lateinit var btnGuardarTarea: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar los campos de entrada y el botón
        edtTitulo = view.findViewById(R.id.edTxtTituloTarea)
        edtDescripcion = view.findViewById(R.id.edTxtDescripcionTarea)
        edtFechaLimite = view.findViewById(R.id.edTxtFechaLimiteTarea)
        btnGuardarTarea = view.findViewById(R.id.btnGuardarTask)

        // Inicializar el ViewModel
        taskCreatorViewModel = ViewModelProvider(this).get(TaskCreatorViewModel::class.java)

        // Configurar el botón para crear la tarea
        btnGuardarTarea.setOnClickListener {
            val titulo = edtTitulo.text.toString()
            val descripcion = edtDescripcion.text.toString()
            val fechaLimite = edtFechaLimite.text.toString()

            // Validar que los campos no estén vacíos
            if (titulo.isNotEmpty() && descripcion.isNotEmpty() && fechaLimite.isNotEmpty()) {
                // Crear la tarea
                val newTask = FbTask(titulo, descripcion, fechaLimite)
                // Subir la tarea a Firebase usando el ViewModel
                taskCreatorViewModel.createTask(newTask)
                // Navegar de vuelta al fragmento de lista de tareas
                findNavController().navigate(R.id.action_taskCreatorFragment2_to_listTasksFragment)

            } else {

                Toast.makeText(requireContext(), "No se ha podido guardar la tarea", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
