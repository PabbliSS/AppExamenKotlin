package org.eurekamps.appexamenkotlin.fragmentsMain

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import org.eurekamps.appexamenkotlin.R


class RegistryFragment : Fragment() {


    lateinit var btnVolver:Button
    lateinit var btnRegistrar:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registry, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVolver = view.findViewById(R.id.btnVolverRegistry)
        btnRegistrar = view.findViewById(R.id.btnRegistrarRegistry)

        btnVolver.setOnClickListener {

            Log.v("REGISTRY", "SE HA PULSADO EL BOTON DE VOLVER")
            findNavController().navigate(R.id.action_registryFragment_to_loginFragment)


        }


        btnRegistrar.setOnClickListener {

            Log.v("REGISTRY", "SE HA PULSADO EL BOTON DE REGISTRAR")
            findNavController().navigate(R.id.action_registryFragment_to_taskCreatorFragment)

        }



    }

}