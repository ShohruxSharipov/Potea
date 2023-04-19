package com.example.potea.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.potea.Button.Button
import com.example.potea.databinding.CategoryButtonBinding

class ButtonAdapter(val list:MutableList<Button>):RecyclerView.Adapter<ButtonAdapter.Holder>() {
    class Holder(binding:CategoryButtonBinding):RecyclerView.ViewHolder(binding.root){
        val btn = binding.btn
        val mainBtn = binding.btnmain
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(CategoryButtonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val btn = list[position]
        holder.btn.text = btn.name
        holder.btn.setOnClickListener {
            holder.btn.setBackgroundColor(Color.GREEN)
            holder.btn.setTextColor(Color.WHITE)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface onclickbtn {
        fun onclickBtn(a:Button)
    }

}