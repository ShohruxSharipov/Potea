package com.example.potea

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.potea.Button.Button
import com.example.potea.Plant.Plant
import com.example.potea.adapter.ButtonAdapter
import com.example.potea.adapter.adapter2
import com.example.potea.databinding.FragmentMyWishlistBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyWishlist.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyWishlist : Fragment() {
    // TODO: Rename and change types of parameters
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
        val binding = FragmentMyWishlistBinding.inflate(inflater,container,false)
        val type2 = object : TypeToken<MutableList<Plant>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val  edit = cache.edit()

        binding.imageView12.setOnClickListener{
            binding.imageView12.visibility = View.INVISIBLE
            binding.textView12.visibility = View.INVISIBLE
            binding.searc.visibility = View.VISIBLE
        }

        var list = mutableListOf<Plant>()
        val str = cache.getString("Items","")
        list = gson.fromJson(str,type2)
        val list2 = mutableListOf<Plant>()
        for (i in list){
            if (i.like == true){
                list2.add(i)
            }
        }
        val btns = mutableListOf<Button>(Button("ALl"),Button("White fl"),Button("Flowers"),Button("Kaktus"))


        if (list2.isEmpty()) {
            binding.imageView17.visibility = View.VISIBLE
            binding.textView28.visibility = View.VISIBLE
            binding.textView29.visibility = View.VISIBLE
        }
        var filter = mutableListOf<Plant>()
        binding.searC.addTextChangedListener {
            filter = mutableListOf()
            for (i in list2){
                if (i.name.contains(it.toString())){
                    filter.add(i)
                }
                Log.d("SAG", "onCreateView: ${filter}")
            }
            val adapter = com.example.potea.adapter.adapter2(filter, requireContext(),object : com.example.potea.adapter.Adapter.ItemClick{
                override fun OnItemClick(plant: Plant) {
                    val item = bundleOf("item" to plant)
                    findNavController().navigate(R.id.action_myWishlist_to_itemFragment, item)
                }},object : com.example.potea.adapter.Adapter.Likee{
                override fun OnLikeClick(position: Int, status: Boolean) {
                    list[position].like = !status
                    Log.d("TAG", "LIKKEE: ${list}")
                    edit.putString("Items",gson.toJson(list)).apply()
                    val s = cache.getString("Items","")
                    list = gson.fromJson(s,type2)
                    Log.d("TAG", "LIKKEE@@@@@: ${list}")
                }

            })
            binding.recyclerView2.adapter = adapter
        }

        val adapter = com.example.potea.adapter.adapter2(list2
            ,requireContext()
            ,object : com.example.potea.adapter.Adapter.ItemClick{
            override fun OnItemClick(plant: Plant) {
                val item = bundleOf("item" to plant)
                findNavController().navigate(R.id.action_myWishlist_to_itemFragment, item)
            }}
            ,object : com.example.potea.adapter.Adapter.Likee{
            override fun OnLikeClick(position: Int, status: Boolean) {
                list[position].like = !status
                Log.d("TAG", "LIKKEE: ${list}")
                edit.putString("Items",gson.toJson(list)).apply()
                val s = cache.getString("Items","")
                list = gson.fromJson(s,type2)
                Log.d("TAG", "LIKKEE@@@@@: ${list}")
            }

        })
        val adapter2 = ButtonAdapter(btns,object : ButtonAdapter.onclickbtn{
            var category = mutableListOf<Plant>()
            override fun onclickBtn(a: Button,b:Boolean) {
                if (b == true){
                    if (a.name == "all"){
                        category = mutableListOf()
                        category = list
                    }else if (a.name == "white fl"){
                        for (i in list){
                            if (i.category == "white fl"){
                                category.add(i)
                            }
                        }
                    }else if (a.name == "Flowers"){
                        for (i in list){
                            if (i.category == "Flowers"){
                                category.add(i)
                            }
                        }
                    }else{
                        for (i in list){
                            if (i.category == "Kaktus"){
                                category.add(i)
                            }
                        }
                    }
                }else{
                    if (a.name == "all"){
                        category = list
                    }else if (a.name == "white fl"){
                        for (i in list){
                            if (i.category == "white fl"){
                                category.remove(i)
                            }
                        }
                    }else if (a.name == "Flowers"){
                        for (i in list){
                            if (i.category == "Flowers"){
                                category.remove(i)
                            }
                        }
                    }else{
                        for (i in list){
                            if (i.category == "Kaktus"){
                                category.remove(i)
                            }
                        }
                    }
                }
                if(category.isEmpty())
                    category = list
                binding.recyclerView2.adapter = adapter2(category, requireContext(),object : com.example.potea.adapter.Adapter.ItemClick{
                    override fun OnItemClick(plant: Plant) {
                        val item = bundleOf("item" to plant)
                        findNavController().navigate(R.id.action_bottomNav_to_myWishlist, item)
                    }},object : com.example.potea.adapter.Adapter.Likee{
                    override fun OnLikeClick(position: Int, status: Boolean) {
                        list[position].like = !status
                        Log.d("TAG", "LIKKEE: ${list}")
                        edit.putString("Items",gson.toJson(list)).apply()
                        val s = cache.getString("Items","")
                        list = gson.fromJson(s,type2)
                        Log.d("TAG", "LIKKEE@@@@@: ${list}")
                    }

                })
            }

        })
        binding.recyclerView.adapter = adapter2
        binding.recyclerView2.adapter = adapter




        binding.back.setOnClickListener {
            val activity : AppCompatActivity = activity as AppCompatActivity
            activity.onBackPressedDispatcher.onBackPressed()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyWishlist.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyWishlist().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}