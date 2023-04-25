package com.example.potea.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.potea.Plant.Plant
import com.example.potea.R
import com.example.potea.databinding.Specialoffers2Binding

class adapter2 (var list:MutableList<Plant>, var context: Context, var itemclick: Adapter.ItemClick,var likee: Adapter.Likee):RecyclerView.Adapter<adapter2.myHolder>(){
    class myHolder(binding: Specialoffers2Binding): RecyclerView.ViewHolder(binding.root){
        var name = binding.name
        var cost = binding.cost
        var image = binding.image
        var like = binding.like
        var card = binding.cardView2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        return myHolder(Specialoffers2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        var like = false
        val anim = AnimationUtils.loadAnimation(context, R.anim.anim_scale)
        var plant = list[position]
        holder.image.setImageResource(plant.img)
        holder.name.text = plant.name
        holder.cost.text = "$" + plant.cost
        if (plant.like == false) {
            holder.like.setImageResource(R.drawable.baseline_favorite_border_24)
        }else holder.like.setImageResource(R.drawable.favorite)

        holder.like.setOnClickListener {
            if (like){
                holder.like.setImageResource(R.drawable.baseline_favorite_border_24)
                likee.OnLikeClick(position,true)
                like = false
            }else{
                holder.like.setImageResource(R.drawable.favorite)
                likee.OnLikeClick(position,false)
                like = true
            }
        }
        holder.card.setOnClickListener {
            itemclick.OnItemClick(plant)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface ItemClick{
        fun OnItemClick(plant: Plant)
    }
}