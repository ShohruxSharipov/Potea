package com.example.potea

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.potea.Plant.Plant
import com.example.potea.databinding.FragmentSplash1Binding
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Splash1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Splash1 : Fragment() {
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

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSplash1Binding.inflate(inflater,container,false)

        val gson = Gson()
        val activity : AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()

        val list = mutableListOf<Plant>()
        list.add(Plant("GUl", "23", R.drawable.prayerplant, false,"Flowers"))
        list.add(Plant("Prayer", "25", R.drawable.caktus, false,"white fl"))
        list.add(Plant("Peperomiya", "40", R.drawable.prayerplant, false,"Flowers"))
        list.add(Plant("Cactus", "50", R.drawable.caktus, false,"Kaktus"))
        list.add(Plant("Vingle", "10", R.drawable.prayerplant, false,"white fl"))
        list.add(Plant("GUl", "23", R.drawable.prayerplant, false,"Flowers"))
        list.add(Plant("Prayer", "25", R.drawable.caktus, false,"white fl"))
        list.add(Plant("Peperomiya", "40", R.drawable.prayerplant, false,"Flowers"))
        list.add(Plant("Cactus", "50", R.drawable.caktus, false,"Kaktus"))
        list.add(Plant("Vingle", "10", R.drawable.prayerplant, false,"white fl"))
        list.add(Plant("GUl", "23", R.drawable.prayerplant, false,"Flowers"))
        list.add(Plant("Prayer", "25", R.drawable.caktus, false,"white fl"))
        list.add(Plant("Peperomiya", "40", R.drawable.prayerplant, false,"Flowers"))
        list.add(Plant("Cactus", "50", R.drawable.caktus, false,"Kaktus"))
        list.add(Plant("Vingle", "10", R.drawable.prayerplant, false,"white fl"))

        var str = gson.toJson(list)
        edit.putString("Items",str).apply()

        binding.next1.setOnClickListener {
            findNavController().navigate(R.id.action_splash1_to_splash2)
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
         * @return A new instance of fragment Splash1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Splash1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}