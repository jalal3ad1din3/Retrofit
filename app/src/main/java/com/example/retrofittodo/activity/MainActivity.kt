package com.example.retrofittodo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofittodo.R
import com.example.retrofittodo.RetrofitInstance
import com.example.retrofittodo.adapter.ToDoAdapter
import com.example.retrofittodo.databinding.ActivityMainBinding
import com.example.retrofittodo.model.ToDo
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

      lateinit var binding: ActivityMainBinding
      lateinit var adapterR: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecview()

          putData()
        binding.progressB.isVisible = false

        binding.btnAdd.setOnClickListener{
        addTodos()
        }

    }

    private fun setRecview() = binding.recyclerV.apply{

        adapterR = ToDoAdapter(this@MainActivity)
        adapter = adapterR
        layoutManager = GridLayoutManager(this@MainActivity,1)


    }

    fun putData(){
        lifecycleScope.launchWhenCreated {
            binding.progressB.isVisible = true

            val responce = try {

                RetrofitInstance.api.getTodos()

            }catch (e: IOException) {
                Log.e("TAG", "IOS")
                binding.progressB.isVisible = false
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e("TAG","HTTP")
                binding.progressB.isVisible = false
                return@launchWhenCreated
            }
            if (responce.isSuccessful && responce.body() != null){
                adapterR.todos = responce.body()!!
                binding.progressB.isVisible = false
            }
            else
                Log.e("TAG","resposn is not succsees")
        }
    }

         fun deleteTodos(todo:ToDo){

          lifecycleScope.launchWhenStarted {
           val response = RetrofitInstance.api.deleteTodos(todo.id)
               if (response.isSuccessful){
                   Log.d("TAG","@@@ success")

                   putData()
               }
              Log.e("RETROFIT_ERROR", response.code().toString())

          }

     }

    fun addTodos(){

          lifecycleScope.launchWhenStarted {
           val response = RetrofitInstance.api.addTodos(33,
           ToDo(false,0,"GAAAAAAA",30)
           )
               if (response.isSuccessful){
                   Log.d("TAG","@@@ ADDDD")
                   putData()
               }
              Log.e("ADD ADDD", response.code().toString())

          }

     }


         /*lifecycleScope.launchWhenCreated {
             binding.progressB.isVisible = true

             val responce = try {

                 RetrofitInstance.api.deleteTodos(todo.id)

             }catch (e: IOException) {
                 Log.e("TAG", "IOS")
                 binding.progressB.isVisible = false
                 return@launchWhenCreated
             }catch (e:HttpException){
                 Log.e("TAG","HTTP")
                 binding.progressB.isVisible = false
                 return@launchWhenCreated
             }
             if (responce.isSuccessful && responce.body() != null){
                 adapterR.todos = responce.body()!!
                 binding.progressB.isVisible = false
             }
             else
                 Log.e("TAG","resposn is not succsees")
         }
         binding.progressB.isVisible = false

         }*/

}