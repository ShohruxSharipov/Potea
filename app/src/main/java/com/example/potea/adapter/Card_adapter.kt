package com.example.potea.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.potea.Plant.Plant
import com.example.potea.databinding.CardPredBinding

class Card_adapter(val list:MutableList<Plant>):RecyclerView.Adapter<Card_adapter.myHolder>() {
    class myHolder(val binding:CardPredBinding):RecyclerView.ViewHolder(binding.root){
        var image = binding.image
        var name = binding.name
        var cost = binding.cost
        var del = binding.del
        var plus = binding.cardView3
        var minus = binding.minus
        var count = binding.count.text.toString().toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        return myHolder(CardPredBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        val item = list[position]
        holder.image.setImageResource(item.img)
        holder.name.text = item.name
        holder.cost.text = item.cost
        holder.del.setOnClickListener{
            list.removeAt(position)
            notifyDataSetChanged()
        }

        holder.plus.setOnClickListener{
            holder.count++
            holder.cost.text = (holder.cost.text.toString().toInt() * holder.count).toString()
            notifyDataSetChanged()
            Log.d("TAG", "onBindViewHolder: +++")
        }
        holder.plus.setOnClickListener{
         if (   holder.count != 0){
             holder.count--
             holder.cost.text = (holder.cost.text.toString().toInt() * holder.count).toString()
             notifyDataSetChanged()
         }
        }
    }
}