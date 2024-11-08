package org.eurekamps.appexamenkotlin.viewmodelsMain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.eurekamps.appexamenkotlin.fbClass.FbTask

class TaskCreatorViewModel(application: Application) : AndroidViewModel(application) {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    // LiveData para manejar el éxito o error al guardar la tarea
    val taskSaveSuccess = MutableLiveData<Boolean>()
    val taskSaveError = MutableLiveData<String>()

    // Función para guardar la tarea
    fun saveTask(titulo: String, descripcion: String, fechaLimite: String) {
        val uid = auth.currentUser?.uid

        if (uid != null) {
            // Verificar que los campos no estén vacíos
            if (titulo.isNotEmpty() && descripcion.isNotEmpty() && fechaLimite.isNotEmpty()) {
                // Crear objeto FbTask
                val tareaData = FbTask(

                    titulo = titulo,
                    descripcion = descripcion,
                    fechaLimite = fechaLimite
                )

                // Guardar la tarea en Firestore
                db.collection("tasks")
                    .document(uid) // Usar UID como ID del documento
                    .collection("user_tasks")
                    .add(tareaData)
                    .addOnSuccessListener {
                        taskSaveSuccess.postValue(true) // Notificar éxito
                    }
                    .addOnFailureListener { e ->
                        taskSaveError.postValue("Error al guardar la tarea: ${e.message}") // Notificar error
                    }
            } else {
                taskSaveError.postValue("Por favor, completa todos los campos.") // Si algún campo está vacío
            }
        } else {
            taskSaveError.postValue("Error: No se ha encontrado el usuario autenticado.")
        }
    }
}
