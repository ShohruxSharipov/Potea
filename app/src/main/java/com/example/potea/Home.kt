package com.example.potea

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.potea.Plant.Plant
import com.example.potea.User.Person
import com.example.potea.User.User
import com.example.potea.adapter.Adapter
import com.example.potea.adapter.adapter2
import com.example.potea.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val type = object : TypeToken<List<Person>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()
        var b = mutableListOf<Person>()

        var a = cache.getString("Profile", "")
        b = gson.fromJson(a, type)
        var user = b[0]
        binding.username.text = user.first_name
//        Toast.makeText(requireContext(), "${b[0].number}", Toast.LENGTH_SHORT).show()

        binding.search.addTextChangedListener {

        }

        var list = mutableListOf<Plant>()
        list.add(Plant("GUl", "$23", R.drawable.prayerplant, false))
        list.add(Plant("PUl", "$25", R.drawable.prayerplant, false))
        list.add(Plant("QUl", "$40", R.drawable.prayerplant, false))
        list.add(Plant("ALI", "$50", R.drawable.prayerplant, false))
        list.add(Plant("VALI", "$10", R.drawable.prayerplant, false))
        val adapter = Adapter(list, requireContext(), object : Adapter.ItemClick {
            override fun OnItemClick(plant: Plant) {
                val item = bundleOf("item" to plant)
                findNavController().navigate(R.id.action_home2_to_itemFragment, item)
            }

        })
        val adapter2 = adapter2(list, requireContext(), object : Adapter.ItemClick {
            override fun OnItemClick(plant: Plant) {
                val item = bundleOf("item" to plant)
                findNavController().navigate(R.id.action_home2_to_itemFragment, item)
            }

        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView2.adapter = adapter2

        var wishlist = mutableListOf<Plant>()
        for (i in list) {
            if (i.like == true) {
                wishlist.add(i)
            }
        }
        binding.wishlist.setOnClickListener {
            for (i in list) {
                if (i.like == true) {
                    wishlist.add(i)
                }
            }
            val bundle = bundleOf("wishlist" to wishlist)
            Log.d("TAG", "sent: ${wishlist.toString()}")

            findNavController().navigate(R.id.action_home2_to_myWishlist, bundle)
        }
        binding.seeall1.setOnClickListener {
            val bundle = bundleOf("wishlist" to list)
            findNavController().navigate(R.id.action_home2_to_myWishlist, bundle)
        }
        binding.seeall2.setOnClickListener {
            val bundle = bundleOf("wishlist" to list)
            findNavController().navigate(R.id.action_home2_to_myWishlist, bundle)
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.cart -> findNavController().navigate(R.id.action_home2_to_cardFragment)
                R.id.profile -> findNavController().navigate(R.id.action_home2_to_profileSetting)
            }
            true
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