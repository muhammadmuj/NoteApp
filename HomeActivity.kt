package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        setupNavigation()
    }

    private fun setupNavigation() {
        val btnAddNote = findViewById<View>(R.id.btn_add_note)
        val btnViewNotes = findViewById<View>(R.id.btn_view_notes)

        btnAddNote.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java)
            intent.putExtra("show_add_note", true)
            startActivity(intent)
        }

        btnViewNotes.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java)
            intent.putExtra("show_view_notes", true)
            startActivity(intent)
        }
    }
}