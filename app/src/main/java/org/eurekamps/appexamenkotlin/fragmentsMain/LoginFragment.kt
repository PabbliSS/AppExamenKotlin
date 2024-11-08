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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.eurekamps.appexamenkotlin.R
import org.eurekamps.appexamenkotlin.viewmodelsMain.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var btnLogin: Button
    private lateinit var btnRegistrarse: Button
    private lateinit var edTxtEmail: EditText
    private lateinit var edTxtPassword: EditText

    // Instancia del LoginViewModel
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin = view.findViewById(R.id.btnLogin)
        btnRegistrarse = view.findViewById(R.id.btnRegistrarse)
        edTxtEmail = view.findViewById(R.id.edTextEmailLogin)
        edTxtPassword = view.findViewById(R.id.edTxtPasswordLogin)

        btnLogin.setOnClickListener {
            Log.v("LOGIN", "SE HA PULSADO EL BOTON DE LOGIN")

            val email = edTxtEmail.text.toString()
            val password = edTxtPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "CORREO Y/O CONTRASEÑA VACÍO/S", Toast.LENGTH_SHORT).show()
            } else {
                // Llamada a iniciar sesión en el ViewModel
                loginViewModel.iniciarSesion(email, password)
            }
        }

        btnRegistrarse.setOnClickListener {
            Log.v("LOGIN", "SE HA PULSADO EL BOTON DE REGISTRARSE")
            findNavController().navigate(R.id.action_loginFragment_to_registryFragment)
        }

        // Observar el éxito del inicio de sesión
        loginViewModel.loginSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                //findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            }
        })

        // Observar el mensaje de error del inicio de sesión
        loginViewModel.loginError.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
