package com.example.potea.P

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.potea.I.Item
import com.example.potea.databinding.CardPredBinding
import com.example.potea.databinding.FragmentCardBinding

class Card_adapter(val list:MutableList<Item>):RecyclerView.Adapter<Card_adapter.myHolder>() {
    class myHolder(val binding:CardPredBinding):RecyclerView.ViewHolder(binding.root){
        var del = binding.del
        var plus = binding.plus
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
        holder.del.setOnClickListener{
            list.removeAt(position)
            notifyDataSetChanged()
        }

        holder.plus.setOnClickListener{
            holder.count++
        }
        holder.plus.setOnClickListener{
         if (   holder.count != 0){
             holder.count--
         }
        }
    }
}