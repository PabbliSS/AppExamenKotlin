package org.eurekamps.appexamenkotlin.fragmentsHome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import org.eurekamps.appexamenkotlin.R

class ListTasksFragment : Fragment() {

    lateinit var btnCrearTarea: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tasks, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCrearTarea = view.findViewById(R.id.btnCrearTarea)

        btnCrearTarea.setOnClickListener {

            findNavController().navigate(R.id.action_listTasksFragment_to_taskCreatorFragment2)
            Log.v("LIST TASKS", "SE HA PULSADO EL BOTON DE CREAR TAREA")

        }

    }

}