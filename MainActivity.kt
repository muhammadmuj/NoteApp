package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        setupNavigation()

        handleIntentExtras()
    }

    private fun handleIntentExtras() {
        val showAddNote = intent.getBooleanExtra("show_add_note", false)
        val showViewNotes = intent.getBooleanExtra("show_view_notes", false)

        if (showAddNote) {
            showAddNoteFragment()
        } else if (showViewNotes) {
            showViewNotesFragment()
        } else {
            showViewNotesFragment()
        }
    }

    private fun setupNavigation() {
        val btnAddNote = findViewById<View>(R.id.btn_add_note)
        val btnViewNotes = findViewById<View>(R.id.btn_view_notes)

        btnAddNote.setOnClickListener {
            showAddNoteFragment()
        }

        btnViewNotes.setOnClickListener {
            showViewNotesFragment()
        }
    }

    private fun showAddNoteFragment() {
        val fragment = AddNoteFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        updateNavigationColors(false)
    }

    private fun showViewNotesFragment() {
        val fragment = ViewNotesFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        updateNavigationColors(true)
    }

    private fun updateNavigationColors(isViewNotesActive: Boolean) {
        val btnAddNote = findViewById<View>(R.id.btn_add_note)
        val btnViewNotes = findViewById<View>(R.id.btn_view_notes)

        val addNoteIcon = btnAddNote.findViewById<ImageView>(R.id.icon_add_note)
        val addNoteText = btnAddNote.findViewById<TextView>(R.id.text_add_note)
        val viewNotesIcon = btnViewNotes.findViewById<ImageView>(R.id.icon_view_notes)
        val viewNotesText = btnViewNotes.findViewById<TextView>(R.id.text_view_notes)

        if (isViewNotesActive) {
            viewNotesIcon.setColorFilter(ContextCompat.getColor(this, android.R.color.black))
            viewNotesText.setTextColor(ContextCompat.getColor(this, android.R.color.black))

            addNoteIcon.setColorFilter(ContextCompat.getColor(this, android.R.color.darker_gray))
            addNoteText.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        } else{
            addNoteIcon.setColorFilter(ContextCompat.getColor(this, android.R.color.black))
            addNoteText.setTextColor(ContextCompat.getColor(this, android.R.color.black))

            viewNotesIcon.setColorFilter(ContextCompat.getColor(this, android.R.color.darker_gray))
            viewNotesText.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        }
    }
}