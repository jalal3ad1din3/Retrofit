package com.example.retrofittodo.model

data class ToDo(
    val completed: Boolean,
    val id: Int,
    val tittle: String,
    val userId: Int
)