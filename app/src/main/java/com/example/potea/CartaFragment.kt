package com.example.potea

import android.annotation.SuppressLint
import android.app.Person
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.potea.Plant.Plant
import com.example.potea.adapter.History
import com.example.potea.databinding.FragmentCartaBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartaFragment : Fragment() {
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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCartaBinding.inflate(inflater,container,false)
        val type = object : TypeToken<MutableList<Plant>>() {}.type
        val tokenn = object : TypeToken<MutableList<com.example.potea.User.Person>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)

        val strt = cache.getString("Profile","")
        var user = mutableListOf<com.example.potea.User.Person>()
            user = gson.fromJson(strt,tokenn)
        Log.d("TAG", "user: ${user}")
        binding.ism.text = "${user[0].first_name} ${user[0].last_name}"
        binding.balance.text = "$${user[0].balance}"
        var list = mutableListOf<Plant>()
        val st = cache.getString("history","")
        Log.d("TAG", "onCreateView: ${st}")
        if (st!!.isNotEmpty()){
            list = gson.fromJson(st,type)
        }

        val adapter = History(list)
        binding.HisRV.adapter = adapter

        var k = 0
        for (i in list){
            k += i.cost.toInt()
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
         * @return A new instance of fragment CartaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}