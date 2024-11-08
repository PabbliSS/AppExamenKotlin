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

class LoginFragment : Fragment() {


    lateinit var btnLogin:Button
    lateinit var btnRegistrarse:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin = view.findViewById(R.id.btnLogin)
        btnRegistrarse = view.findViewById(R.id.btnRegistrarse)

        btnLogin.setOnClickListener{

            Log.v("LOGIN", "SE HA PULSADO EL BOTON DE LOGIN")

        }


        btnRegistrarse.setOnClickListener {

            Log.v("LOGIN", "SE HA PULSADO EL BOTON DE REGISTRARSE")
            findNavController().navigate(R.id.action_loginFragment_to_registryFragment)

        }

    }


}