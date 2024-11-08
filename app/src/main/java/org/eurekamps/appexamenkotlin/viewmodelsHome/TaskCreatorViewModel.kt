package org.eurekamps.appexamenkotlin.viewmodelsMain

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.database.FirebaseDatabase
import org.eurekamps.appexamenkotlin.fbClass.FbTask

class TaskCreatorViewModel(application: Application) : AndroidViewModel(application) {

    private val database = FirebaseDatabase.getInstance().getReference("tasks")

    // Función para subir una tarea a Firebase
    fun createTask(task: FbTask) {
        // Generar una nueva ID para la tarea
        val taskId = database.push().key ?: return

        // Subir la tarea a Firebase bajo una nueva ID
        database.child(taskId).setValue(task).addOnCompleteListener { taskResult ->
            if (taskResult.isSuccessful) {

                Log.v("TASK CREATOR", "SE HA GUARDADO LA TAREA")

            } else {

                Log.e("TASK CREATOR", "NO SE HA GUARDADO LA TAREA")

            }
        }
    }
}
