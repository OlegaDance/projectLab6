package com.example.myapplication5

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class LesAdapter(private var lessons: MutableList<Lesson>) : RecyclerView.Adapter<LesAdapter.ViewHolder>() {

    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_lesson_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.bind(lesson)

        fun showEditLessonDialog(lesson: Lesson, position: Int) {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Редагувати")

            val view = LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialog_less, null)
            builder.setView(view)

            val LessName = view.findViewById<EditText>(R.id.LessName)
            val LessStart = view.findViewById<EditText>(R.id.LessStart)
            val LessEnd = view.findViewById<EditText>(R.id.LessEnd)
            LessName.setText(lesson.name)
            LessStart.setText(lesson.startTime)
            LessEnd.setText(lesson.endTime)

            builder.setPositiveButton("Оновити") { dialog,_ ->
                val LessName = LessName.text.toString()
                val LessStart = LessStart.text.toString()
                val LessEnd = LessEnd.text.toString()
                if (LessName.isNotEmpty() && LessStart.isNotEmpty() && LessEnd.isNotEmpty()) {
                    val updatedTeacher = Lesson(LessName, LessStart, LessEnd)
                    updateLesson(position, updatedTeacher)
                } else {
                    Toast.makeText(holder.itemView.context,"Заповніть поля" , Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }

            builder.setNegativeButton("Скасувати") { dialog,_ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
        holder.itemView.setOnClickListener {
            showEditLessonDialog(lesson, position)
        }
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
    }

    fun addItem(lesson: Lesson) {
        lessons.add(lesson)
        notifyItemInserted(lessons.size - 1)
    }

    fun removeItem(position: Int) {
        lessons.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(lesson: Lesson) {
            itemView.findViewById<TextView>(R.id.LessName).text = lesson.name
            itemView.findViewById<TextView>(R.id.tvTime).text = "${lesson.startTime} - ${lesson.endTime}"

            itemView.findViewById<Button>(R.id.btnDelete).setOnClickListener {
                lessons.removeAt(position)
                notifyItemRemoved(position)
            }

        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateLesson(position: Int, updatedLesson: Lesson) {
        val updatedList = lessons.toMutableList()
        updatedList[position] = updatedLesson
        lessons = updatedList
        notifyItemChanged(position)
    }
}