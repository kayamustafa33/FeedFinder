package com.mobile.feedfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.feedfinder.databinding.RecyclerHomeBinding
import com.mobile.feedfinder.model.Animals

class HomeAdapter(private val animalList : ArrayList<Animals>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    class ViewHolder(val binding : RecyclerHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return animalList.size
    }


}