package com.example.retrofittodo.APi

import com.example.retrofittodo.model.ToDo
import retrofit2.Response
import retrofit2.http.*

interface ToDoAPi {

    @GET("todos")
    suspend fun getTodos():Response<List<ToDo>>

    @PUT("todos/{id}")
    suspend fun addTodos(@Path("id")itemID: Int,@Body toDo: ToDo):Response<ToDo>

    @Multipart
    fun

    @DELETE("todos/{id}")
    suspend fun deleteTodos(@Path("id")itemID:Int):Response<ToDo>
}