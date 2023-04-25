package com.example.potea

import android.annotation.SuppressLint
import android.app.Dialog
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
import com.example.potea.Plant.Plant
import com.example.potea.User.Person
import com.example.potea.adapter.Adapter
import com.example.potea.adapter.adapter2
import com.example.potea.databinding.CustomDialogBinding
import com.example.potea.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.FieldPosition
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Home : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("CommitPrefEdits", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val type = object : TypeToken<List<Person>>() {}.type
        val type2 = object : TypeToken<MutableList<Plant>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()
        val b: MutableList<Person>

        val a = cache.getString("Profile", "")
        b = gson.fromJson(a, type)
        val user = b[0]
        binding.username.text = user.first_name
        var list = mutableListOf<Plant>()
//        Toast.makeText(requireContext(), "${b[0].number}", Toast.LENGTH_SHORT).show()

        binding.search.addTextChangedListener {

        }
        val s = cache.getString("Items","")
        list = gson.fromJson(s,type2)

//        Log.d("TAG", "1st xolat"+"${list[0].like}")
        val adapter = Adapter(list, requireContext(), object : Adapter.ItemClick {
            override fun OnItemClick(plant: Plant) {
                val item = bundleOf("item" to plant)
                findNavController().navigate(R.id.action_bottomNav_to_itemFragment, item)
            }

        },object : Adapter.Likee{
            override fun OnLikeClick(position: Int, status: Boolean) {
                list[position].like = !status
                Log.d("TAG", "LIKKEE: ${list}")
                edit.putString("Items",gson.toJson(list)).apply()
                val s = cache.getString("Items","")
                list = gson.fromJson(s,type2)
                Log.d("TAG", "LIKKEE@@@@@: ${list}")
            }

        })
        val adapter2 = adapter2(list, requireContext(), object : Adapter.ItemClick {
            override fun OnItemClick(plant: Plant) {
                val item = bundleOf("item" to plant)
                findNavController().navigate(R.id.action_bottomNav_to_itemFragment, item)
            }

        },object : Adapter.Likee{
            override fun OnLikeClick(position: Int, status: Boolean) {
                list[position].like = !status
                Log.d("TAG", "LIKKEE: ${list}")
                edit.putString("Items",gson.toJson(list)).apply()
                val s = cache.getString("Items","")
                list = gson.fromJson(s,type2)
                Log.d("TAG", "LIKKEE@@@@@: ${list}")
            }

        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView2.adapter = adapter2

        val wishlist = mutableListOf<Plant>()
        for (i in list) {
            if (i.like) {
                wishlist.add(i)
            }
        }
        binding.wishlist.setOnClickListener {
            for (i in list) {
                if (i.like) {
                    wishlist.add(i)
                }
            }
            val bundle = bundleOf("wishlist" to wishlist)

            findNavController().navigate(R.id.action_bottomNav_to_myWishlist, bundle)
        }
        binding.seeall1.setOnClickListener {
            val bundle = bundleOf("wishlist" to list)
            findNavController().navigate(R.id.action_bottomNav_to_myWishlist, bundle)
        }
        binding.seeall2.setOnClickListener {
            val bundle = bundleOf("wishlist" to list)
            findNavController().navigate(R.id.action_bottomNav_to_myWishlist, bundle)
        }


        binding.search.addTextChangedListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.custom_dialog)
            dialog.setCancelable(true)
            dialog.show()

            val filter = mutableListOf<Plant>()
            if (it != null) {
                for (i in list){
                    if (i.name.contains(it)){
                        filter.add(i)
                    }
                }
            }
//            Log.d("TAG", "onCreateView: ${filter}")

            val bind = CustomDialogBinding.inflate(inflater,container,false)

            val a = adapter2(list, requireContext(), object : Adapter.ItemClick {
                override fun OnItemClick(plant: Plant) {
                    val item = bundleOf("item" to plant)
                    findNavController().navigate(R.id.action_bottomNav_to_itemFragment, item)
                }

            },object : Adapter.Likee{
                override fun OnLikeClick(position: Int, status: Boolean) {
                    list[position].like = !status
                    Log.d("TAG", "LIKKEE: ${list}")
                    edit.putString("Items",gson.toJson(list)).apply()
                    val s = cache.getString("Items","")
                    list = gson.fromJson(s,type2)
                    Log.d("TAG", "LIKKEE@@@@@: ${list}")
                }

            })
            bind.searchRV.adapter = a

        }


        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}