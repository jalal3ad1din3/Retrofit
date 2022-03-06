package com.example.retrofittodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittodo.activity.MainActivity
import com.example.retrofittodo.model.ToDo
import com.example.retrofittodo.databinding.ItemFeedBinding

class ToDoAdapter(val activity: MainActivity): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(val binding: ItemFeedBinding):RecyclerView.ViewHolder(binding.root){
        val ll_delete = binding.llDelete
    }

    private val difcallback = object:DiffUtil.ItemCallback<ToDo>(){

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
           return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private val differ = AsyncListDiffer(this,difcallback)
    var todos: List<ToDo>
      get() = differ.currentList
    set(value) { differ.submitList(value)}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
       return ToDoViewHolder(ItemFeedBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,
            false))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = todos[position]
        holder.binding.tvJust.text = item.tittle
        holder.binding.checkbox.isChecked = item.completed

    holder.ll_delete.setOnClickListener {
           activity.deleteTodos(item)
        }
    }

    override fun getItemCount(): Int {
       return todos.size
    }

}