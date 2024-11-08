package org.eurekamps.appexamenkotlin.viewmodelsMain

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // LiveData para manejar el éxito del inicio de sesión
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    // LiveData para manejar el mensaje de error en el inicio de sesión
    private val _loginError = MutableLiveData<String?>()
    val loginError: LiveData<String?> get() = _loginError

    fun iniciarSesion(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("LoginViewModel", "Inicio de sesión exitoso")
                    _loginSuccess.value = true
                    _loginError.value = null
                // Limpiamos el error si fue exitoso
                } else {
                    Log.d("LoginViewModel", "Inicio de sesión fallido")
                    _loginSuccess.value = false
                    _loginError.value = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> "El correo electrónico no está registrado."
                        is FirebaseAuthInvalidCredentialsException -> "La contraseña es incorrecta."
                        is FirebaseAuthException -> "Error: ${task.exception?.message}"
                        else -> "Error desconocido."
                    }
                }
            }
    }
}
