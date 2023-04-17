package com.example.potea.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.potea.Plant.Plant
import com.example.potea.R
import com.example.potea.databinding.FragmentHomeBinding
import com.example.potea.databinding.SpecialoffersBinding

class Adapter(var list:MutableList<Plant>,var context:Context, var itemclick:ItemClick):RecyclerView.Adapter<Adapter.MyHolder>(){

    class MyHolder(binding:SpecialoffersBinding):RecyclerView.ViewHolder(binding.root){
        var name = binding.name
        var cost = binding.cost
        var image = binding.image
        var like = binding.like
        var card = binding.cardView2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(SpecialoffersBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var like = false
        val anim = AnimationUtils.loadAnimation(context,R.anim.anim_scale)
        var plant = list[position]
        holder.image.setImageResource(plant.img)
        holder.name.text = plant.name
        holder.cost.text = plant.cost
        if (plant.like == false) {
            holder.like.setImageResource(R.drawable.baseline_favorite_border_24)
        }else holder.like.setImageResource(R.drawable.favorite)

        holder.like.setOnClickListener {
            Toast.makeText(context, "LIKEE", Toast.LENGTH_SHORT).show()
            if (plant.like == false){
                holder.like.setImageResource(R.drawable.favorite)
                holder.like.startAnimation(anim)
                plant.like = true
            }else {
                holder.like.setImageResource(R.drawable.baseline_favorite_border_24)
                holder.like.startAnimation(anim)
                plant.like = false
            }

        }
        holder.card.setOnClickListener {
            itemclick.OnItemClick(plant)
        }
    }
    interface ItemClick{
        fun OnItemClick(plant: Plant)
    }
}