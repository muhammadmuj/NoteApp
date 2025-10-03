package com.example.myapplication

data class Note(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis() // Add creation timestamp
)