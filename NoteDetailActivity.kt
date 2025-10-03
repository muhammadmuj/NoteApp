package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var buttonBack: ImageView
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        supportActionBar?.hide()

        textViewTitle = findViewById(R.id.text_view_title)
        textViewDescription = findViewById(R.id.text_view_description)
        buttonBack = findViewById(R.id.button_back)
        sharedPreferencesHelper = SharedPreferencesHelper(this)

        setupBackButton()
        displayNoteDetails()
    }

    private fun setupBackButton() {
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun displayNoteDetails() {
        val noteId = intent.getLongExtra("note_id", -1L)

        if (noteId != -1L) {
            val note = sharedPreferencesHelper.getNoteById(noteId)

            if (note != null) {
                textViewTitle.text = note.title
                textViewDescription.text = note.description
            } else {
                finish()
            }
        } else {
            finish()
        }
    }
}