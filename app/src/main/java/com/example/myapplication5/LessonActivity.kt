package com.example.myapplication5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LessonActivity : AppCompatActivity() {
    lateinit var adapter: LesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLessons)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LesAdapter(getLessons())
        recyclerView.adapter = adapter
        findViewById<Button>(R.id.btnADD).setOnClickListener {
            showAddLessonDialog()
        }

    }
    private fun showAddLessonDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Додати пару")

        val view = layoutInflater.inflate(R.layout.dialog_less, null)
        builder.setView(view)

        val LessName = view.findViewById<EditText>(R.id.LessName)
        val LessStart = view.findViewById<EditText>(R.id.LessStart)
        val LessEnd = view.findViewById<EditText>(R.id.LessEnd)

        builder.setPositiveButton("Додати") { dialog, _ ->
            val LessName = LessName.text.toString()
            val LessStart = LessStart.text.toString()
            val LessEnd = LessEnd.text.toString()
            if (LessName.isNotEmpty() && LessStart.isNotEmpty() && LessEnd.isNotEmpty()) {
                val newLesson = Lesson(LessName, LessStart, LessEnd)
                adapter.addItem(newLesson)
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

    private fun getLessons(): MutableList<Lesson> {
        return mutableListOf(
            Lesson( "Math Lesson", "10:00 AM", "12:00 PM"),
            Lesson( "English Lesson", "1:00 PM", "3:00 PM"),
            Lesson( "Physics Lesson", "3:00 PM", "5:00 PM")
        )
    }
}