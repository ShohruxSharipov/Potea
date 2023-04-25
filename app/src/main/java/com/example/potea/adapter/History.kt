package com.example.potea.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.potea.Plant.Plant
import com.example.potea.databinding.HistoryBinding

class History(var list: MutableList<Plant>): RecyclerView.Adapter<History.HistoryHolder>() {
    class HistoryHolder(binding: HistoryBinding): RecyclerView.ViewHolder(binding.root){
        var name = binding.textView24
        var image = binding.imageView8
        var cost = binding.textView26
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        return HistoryHolder(HistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val plant = list[position]
        holder.cost.text = "$${ plant.cost }"
        holder.image.setImageResource(plant.img)
        holder.name.text = plant.name
    }
}