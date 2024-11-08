package org.eurekamps.appexamenkotlin.fragmentsMain

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.eurekamps.appexamenkotlin.R
import org.eurekamps.appexamenkotlin.viewmodelsMain.RegistryViewModel


class RegistryFragment : Fragment() {

    lateinit var edTxtEmailReg: EditText
    lateinit var edTxtPasswordReg: EditText
    lateinit var edTxtRepPasswordReg: EditText

    lateinit var btnVolver:Button
    lateinit var btnRegistrar:Button



    private val registryViewModel: RegistryViewModel by viewModels()


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

        edTxtEmailReg = view.findViewById(R.id.edTxtEmailRegistry)
        edTxtPasswordReg = view.findViewById(R.id.edTxtPasswordRegistry)
        edTxtRepPasswordReg = view.findViewById(R.id.edTxtRepPasswordRegistry)

        btnVolver = view.findViewById(R.id.btnVolverRegistry)
        btnRegistrar = view.findViewById(R.id.btnRegistrarRegistry)

        btnVolver.setOnClickListener {

            Log.v("REGISTRY", "SE HA PULSADO EL BOTON DE VOLVER")
            findNavController().navigate(R.id.action_registryFragment_to_loginFragment)


        }


        btnRegistrar.setOnClickListener {

            val email = edTxtEmailReg.text.toString()
            val password = edTxtPasswordReg.text.toString()
            val repassword = edTxtRepPasswordReg.text.toString()

            // Verificar si los campos están llenos
            if (email.isNotEmpty() && password.isNotEmpty() && repassword.isNotEmpty()) {

                // Verificar que la contraseña tenga al menos 6 caracteres
                if (password.length >= 6) {

                    // Verificar si las contraseñas coinciden
                    if (password == repassword) {

                        Log.d("REGISTRY", "Se ha pulsado el botón Registrar")
                        registryViewModel.registroUsuario(email, password)

                        Toast.makeText(requireContext(), "Se ha completado el registro exitosamente", Toast.LENGTH_SHORT).show()
                        // Navegar a la siguiente pantalla solo si la validación es exitosa

                        findNavController().navigate(R.id.action_registryFragment_to_loginFragment)

                    } else {

                        Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()

                    }


                } else {

                    Toast.makeText(requireContext(), "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()

                }

            } else {

                Toast.makeText(requireContext(), "Hay campos vacíos", Toast.LENGTH_SHORT).show()
            }

            Log.v("REGISTRY", "SE HA PULSADO EL BOTON DE REGISTRAR")

        }


    }

}