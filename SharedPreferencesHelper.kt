package com.example.myapplication

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("notes_app", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveNote(note: Note) {
        val notesList = getAllNotes().toMutableList()
        notesList.add(0, note) // Add to beginning for newest first

        val notesJson = gson.toJson(notesList)
        sharedPreferences.edit().putString("notes", notesJson).apply()
    }

    fun getAllNotes(): List<Note> {
        val notesJson = sharedPreferences.getString("notes", "[]")
        val type = object : TypeToken<List<Note>>() {}.type
        return gson.fromJson(notesJson, type) ?: emptyList()
    }

    fun getNoteById(noteId: Long): Note? {
        return getAllNotes().find { it.id == noteId }
    }

    fun deleteNote(noteId: Long) {
        val notesList = getAllNotes().toMutableList()
        notesList.removeAll { it.id == noteId }

        val notesJson = gson.toJson(notesList)
        sharedPreferences.edit().putString("notes", notesJson).apply()
    }
}