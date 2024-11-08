package org.eurekamps.appexamenkotlin.viewmodelsMain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import org.eurekamps.appexamenkotlin.fbClass.FbTask

class ListTasksViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("tasks")
    private val _taskList = MutableLiveData<List<FbTask>>()
    val taskList: LiveData<List<FbTask>> get() = _taskList

    init {
        loadTasks()
    }

    // Cargar tareas desde Firebase
    private fun loadTasks() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tasks = mutableListOf<FbTask>()
                for (taskSnapshot in snapshot.children) {
                    val task = taskSnapshot.getValue(FbTask::class.java)
                    if (task != null) {
                        tasks.add(task)
                    }
                }
                _taskList.value = tasks
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error if needed
            }
        })
    }
}
