package org.eurekamps.appexamenkotlin.fragmentsHome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.eurekamps.appexamenkotlin.R
import org.eurekamps.appexamenkotlin.viewmodelsMain.TaskCreatorViewModel

class TaskCreatorFragment : Fragment() {

    private lateinit var edtTitulo: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var edtFechaLimite: EditText
    private lateinit var btnCrearTarea: Button

    // Instancia del TaskViewModel
    private val taskViewModel: TaskCreatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtTitulo = view.findViewById(R.id.edTxtTituloTarea)
        edtDescripcion = view.findViewById(R.id.edTxtDescripcionTarea)
        edtFechaLimite = view.findViewById(R.id.edTxtFechaLimiteTarea)
        btnCrearTarea = view.findViewById(R.id.btnCrearTarea)

        // Configurar el botón para guardar la tarea
        btnCrearTarea.setOnClickListener {
            val titulo = edtTitulo.text.toString()
            val descripcion = edtDescripcion.text.toString()
            val fechaLimite = edtFechaLimite.text.toString()

            if (titulo.isNotEmpty() && descripcion.isNotEmpty() && fechaLimite.isNotEmpty()) {
                // Llamar al ViewModel para guardar la tarea
                taskViewModel.saveTask(titulo, descripcion, fechaLimite)
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        // Observar el éxito al guardar la tarea
        taskViewModel.taskSaveSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                Toast.makeText(requireContext(), "Tarea guardada exitosamente.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_taskCreatorFragment2_to_listTasksFragment) // Navegar a ListTasksFragment
            }
        })

        // Observar el error al guardar la tarea
        taskViewModel.taskSaveError.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
