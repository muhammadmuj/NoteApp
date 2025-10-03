package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.Note

class ViewNotesFragment : Fragment() {

    private lateinit var linearLayoutNotes: LinearLayout
    private lateinit var textViewEmpty: TextView
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_notes, container, false)

        linearLayoutNotes = view.findViewById(R.id.linear_layout_notes)
        textViewEmpty = view.findViewById(R.id.text_view_empty)
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        return view
    }

    override fun onResume() {
        super.onResume()
        displayNotes()
    }

    private fun displayNotes() {
        linearLayoutNotes.removeAllViews()

        val notes = sharedPreferencesHelper.getAllNotes()

        if (notes.isEmpty()) {
            textViewEmpty.visibility = View.VISIBLE
            linearLayoutNotes.visibility = View.GONE
        } else {
            textViewEmpty.visibility = View.GONE
            linearLayoutNotes.visibility = View.VISIBLE

            for (note in notes) {
                addNoteToView(note)
            }
        }
    }

    private fun addNoteToView(note: Note) {
        val noteView = LayoutInflater.from(requireContext())
            .inflate(R.layout.item_note, linearLayoutNotes, false)

        val textViewTitle = noteView.findViewById<TextView>(R.id.text_view_note_title)
        val textViewPreview = noteView.findViewById<TextView>(R.id.text_view_note_preview)
        val textViewDate = noteView.findViewById<TextView>(R.id.text_view_note_date)

        textViewTitle.text = note.title

        val preview = if (note.description.length > 100) {
            note.description.substring(0, 100) + "..."
        } else {
            note.description
        }
        textViewPreview.text = preview

        textViewDate.text = formatDate(note.id)

        // Set click listener to open note details
        noteView.setOnClickListener {
            val intent = Intent(requireContext(), NoteDetailActivity::class.java)
            intent.putExtra("note_id", note.id)
            startActivity(intent)
        }

        linearLayoutNotes.addView(noteView)
    }

    private fun formatDate(timestamp: Long): String {
        val date = java.util.Date(timestamp)
        val format = java.text.SimpleDateFormat("MMM dd, yyyy - hh:mm a", java.util.Locale.getDefault())
        return format.format(date)
    }
}