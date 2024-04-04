package com.example.myapplication5


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TeacherActivity : AppCompatActivity() {
    lateinit var adapter: TeachAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)
        findViewById<Button>(R.id.btnADD).setOnClickListener {
            showAddTeacherDialog()
        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTeachers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TeachAdapter(getTeachers())
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btn_back).setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

    }

    private fun showAddTeacherDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Додати пару")

        val view = layoutInflater.inflate(R.layout.dialog_teach, null)
        builder.setView(view)

        val TeacherName = view.findViewById<EditText>(R.id.tvTeacherName)
        val Subject = view.findViewById<EditText>(R.id.tvSubject)


        builder.setPositiveButton("Додати") { dialog, _ ->
            val TeacherName = TeacherName.text.toString()
            val Subject = Subject.text.toString()

            if (TeacherName.isNotEmpty() && Subject.isNotEmpty()) {
                val newTeacher = Teacher(TeacherName, Subject)
                adapter.addItem(newTeacher)
            } else {
                Toast.makeText(this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }


        builder.setNegativeButton("Відмінити") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }




    private fun getTeachers(): MutableList<Teacher> {

        return mutableListOf(
            Teacher( "John Doe", "Math"),
            Teacher( "Jane Smith", "English"),
            Teacher( "Michael Johnson", "Physics")
        )


    }
}

private fun getTeachers(): MutableList<Teacher> {

    return mutableListOf(
        Teacher( "John Doe", "Math"),
        Teacher( "Jane Smith", "English"),
        Teacher( "Michael Johnson", "Physics")
    )


}