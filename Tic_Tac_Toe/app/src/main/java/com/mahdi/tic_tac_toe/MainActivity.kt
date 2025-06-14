package com.example.notepad

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mahdi.tic_tac_toe.R

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNote: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNote = findViewById(R.id.editTextNote)
        buttonSave = findViewById(R.id.buttonSave)

        // Load saved note
        val sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE)
        val savedNote = sharedPreferences.getString("note", "")
        editTextNote.setText(savedNote)

        // Save note when button is clicked
        buttonSave.setOnClickListener {
            val note = editTextNote.text.toString()
            if (note.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("note", note)
                editor.apply()
                Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please write something", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
