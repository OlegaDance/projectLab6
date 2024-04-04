package com.example.myapplication5


import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication5.AuthActivity
import com.example.myapplication5.LessonActivity


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        findViewById<Button>(R.id.btn_logout).setOnClickListener {

        }

        val btnTeachers = findViewById<Button>(R.id.btn_teachers)
        val btnLessons = findViewById<Button>(R.id.btn_lessons)

        btnTeachers.setOnClickListener {
            startActivity(Intent(this, TeacherActivity::class.java))
        }

        btnLessons.setOnClickListener {
            startActivity(Intent(this, LessonActivity::class.java))
        }

        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }
    }
}