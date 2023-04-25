package com.example.potea

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.potea.Plant.Plant
import com.example.potea.User.User
import com.example.potea.databinding.FragmentItemBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ItemFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemBinding.inflate(inflater,container,false)
        val item = arguments?.getSerializable("item") as Plant
        binding.name.text = item.name
        binding.imageView13.setImageResource(item.img)
        binding.cost.text = item.cost
        binding.back.setOnClickListener {
            val activity : AppCompatActivity = activity as AppCompatActivity
            activity.onBackPressedDispatcher.onBackPressed()
        }


        val type = object : TypeToken<List<Plant>>() {}.type
        val gson = Gson()

        val activity :  AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()

        var str = cache.getString("card","")
        var card_list = mutableListOf<Plant>()
        Log.d("TAG", "onCreateView: ${str}")


        if (!item.like){
            binding.like.setBackgroundResource(R.drawable.baseline_favorite_border_24)
        }else binding.like.setBackgroundResource(R.drawable.favorite)

        binding.like.setOnClickListener{
            it.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.anim_scale))
            if (item.like){
                it.setBackgroundResource(R.drawable.baseline_favorite_border_24)
                item.like = false
            }else {
                it.setBackgroundResource(R.drawable.favorite)
                item.like = true
            }
        }

        binding.plus.setOnClickListener {
            binding.count.text = (binding.count.text.toString().toInt() + 1).toString()
            binding.cost.text = (item.cost.toInt() * binding.count.text.toString().toInt()).toString()
        }
        binding.minus.setOnClickListener {
            if (binding.count.text.toString().toInt() != 1)
            binding.count.text = (binding.count.text.toString().toInt() - 1).toString()
            binding.cost.text = (item.cost.toInt() * binding.count.text.toString().toInt()).toString()
        }

        binding.button.setOnClickListener{
            if (str != ""){
                card_list = gson.fromJson(str,type)
                card_list.add(item)
                var a = gson.toJson(card_list)
                edit.putString("card",a).apply()
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
            }else {
                card_list.add(item)
                var a = gson.toJson(card_list)
                edit.putString("card",a).apply()
                Toast.makeText(requireContext(), "1st item", Toast.LENGTH_SHORT).show()
            }
            Log.d("TAG", "CARD_LIST: ${card_list.toString()}")
        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}