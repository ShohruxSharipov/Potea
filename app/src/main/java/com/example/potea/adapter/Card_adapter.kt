package com.example.potea.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.potea.Plant.Plant
import com.example.potea.databinding.CardPredBinding
import com.example.potea.dialog.MyDialogFragment
import okhttp3.internal.notifyAll

class Card_adapter(val list:MutableList<Plant>,var del:delete):RecyclerView.Adapter<Card_adapter.myHolder>() {
    class myHolder(val binding:CardPredBinding):RecyclerView.ViewHolder(binding.root){
        var image = binding.image
        var name = binding.name
        var cost = binding.cost
        var del = binding.del
        var plus = binding.plus
        var minus = binding.minus
        var count = binding.count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        return myHolder(CardPredBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        val item = list[position]
        var count = 1
        holder.image.setImageResource(item.img)
        holder.name.text = item.name
        holder.cost.text = item.cost
        holder.del.setOnClickListener{
            del.del(item)
            notifyDataSetChanged()
        }

        holder.plus.setOnClickListener{
            count++
            holder.cost.text = (item.cost.toInt() * count).toString()
            holder.count.text = count.toString()
//            notifyItemChanged(position)
            Log.d("SH", "count: +++${holder.cost}")
        }
        holder.minus.setOnClickListener{
         if ( count != 1){
             count--
             holder.cost.text = (item.cost.toInt() * count).toString()
             holder.count.text = count.toString()
//             notifyItemChanged(position)
         }
        }
    }
    interface delete{
        fun del(a:Plant)
    }
}