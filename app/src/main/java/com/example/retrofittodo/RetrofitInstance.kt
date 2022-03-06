package com.example.retrofittodo

import com.example.retrofittodo.APi.ToDoAPi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: ToDoAPi by lazy {
        Retrofit.Builder()
            .baseUrl("https://6221acb5afd560ea69b6849c.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ToDoAPi::class.java)

    }

}