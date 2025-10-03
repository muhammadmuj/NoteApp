package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment


class AddNoteFragment : Fragment() {
    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonBack: ImageView
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)

        editTextTitle = view.findViewById(R.id.edit_text_title)
        editTextDescription = view.findViewById(R.id.edit_text_description)
        buttonSave = view.findViewById(R.id.button_save)
        buttonBack = view.findViewById(R.id.button_back)
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        setupClickListeners()

        return view
    }
    private fun setupClickListeners() {
        buttonSave.setOnClickListener {
            saveNote()
        }
        buttonBack.setOnClickListener {
            goBackToHome()
        }
    }
    private fun saveNote() {
        val title = editTextTitle.text.toString().trim()
        val description = editTextDescription.text.toString().trim()

        if (title.isEmpty()) {
            editTextTitle.error = "Please enter a title"
            return
        }
        if (description.isEmpty()) {
            editTextDescription.error = "Please enter a description"
            return
        }
        val noteId = System.currentTimeMillis()
        val newNote = Note(noteId, title, description)
        sharedPreferencesHelper.saveNote(newNote)
        Toast.makeText(requireContext(), "Note saved successfully!", Toast.LENGTH_SHORT).show()

        goBackToHome()
    }
    private fun goBackToHome() {
        val intent = android.content.Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}