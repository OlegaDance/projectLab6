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

class TeachAdapter(private var teachers: MutableList<Teacher>) : RecyclerView.Adapter<TeachAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_teacher_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher)



        fun showEditTeacherDialog(teacher: Teacher, position: Int) {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Редагувати")

            val view =
                LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialog_teach, null)
            builder.setView(view)

            val tvTeacherName = view.findViewById<EditText>(R.id.tvTeacherName)
            val tvSubject = view.findViewById<EditText>(R.id.tvSubject)

            tvTeacherName.setText(teacher.name)
            tvSubject.setText(teacher.subject)


            builder.setPositiveButton("Оновити") { dialog, _ ->
                val tvTeacherName = tvTeacherName.text.toString()
                val tvSubject = tvSubject.text.toString()

                if (tvTeacherName.isNotEmpty() && tvSubject.isNotEmpty()) {
                    val updatedTeacher = Teacher(tvTeacherName, tvSubject)
                    updateTeacher(position, updatedTeacher)
                } else {
                    Toast.makeText(holder.itemView.context, "Заповніть поля", Toast.LENGTH_SHORT)
                        .show()
                }
                dialog.dismiss()
            }

            builder.setNegativeButton("Скасувати") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
        holder.itemView.setOnClickListener {
            showEditTeacherDialog(teacher, position)
        }
    }


    override fun getItemCount(): Int {
        return teachers.size
    }

    fun addItem(teacher: Teacher) {
        teachers.add(teacher)
        notifyItemInserted(teachers.size - 1)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(teacher: Teacher) {
            itemView.findViewById<TextView>(R.id.tvTeacherName).text = teacher.name
            itemView.findViewById<TextView>(R.id.tvSubject).text = teacher.subject

            itemView.findViewById<Button>(R.id.btnDelete).setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    teachers.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateTeacher(position: Int, updateTeacher: Teacher) {
        val updatedList = teachers.toMutableList()
        updatedList[position] = updateTeacher
        teachers = updatedList
        notifyItemChanged(position)
    }
}