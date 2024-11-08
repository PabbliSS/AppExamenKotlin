package org.eurekamps.appexamenkotlin.fragmentsHome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import org.eurekamps.appexamenkotlin.R


class TaskCreatorFragment : Fragment() {


    lateinit var edTxtTituloTarea:EditText
    lateinit var edTxtDescripcionTarea:EditText
    lateinit var edTxtFechaLimiteTarea:EditText

    lateinit var btnVolverCreadorTareas:Button
    lateinit var btnGuardarTarea:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        edTxtTituloTarea = view.findViewById(R.id.edTxtTituloTarea)
        edTxtDescripcionTarea = view.findViewById(R.id.edTxtDescripcionTarea)
        edTxtFechaLimiteTarea = view.findViewById(R.id.edTxtFechaLimiteTarea)

        btnVolverCreadorTareas = view.findViewById(R.id.btnVolverTask)
        btnGuardarTarea = view.findViewById(R.id.btnGuardarTask)


        btnVolverCreadorTareas.setOnClickListener {

            findNavController().navigate(R.id.action_taskCreatorFragment2_to_listTasksFragment)
            Log.v("TASK CREATOR", "SE HA PULSADO EL BOTON VOLVER")

        }

        btnGuardarTarea.setOnClickListener {

            Log.v("TASK CREATOR", "SE HA PULSADO EL BOTON GUARDAR TAREA")

        }


    }

}