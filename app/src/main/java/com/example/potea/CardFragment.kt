package com.example.potea

import android.annotation.SuppressLint
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
import com.example.potea.adapter.Card_adapter
import com.example.potea.databinding.FragmentCardBinding
import com.example.potea.dialog.MyDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var bind: FragmentCardBinding

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
        bind = FragmentCardBinding.inflate(inflater, container, false)
        var list = mutableListOf<Plant>()
        val type = object : TypeToken<List<Plant>>() {}.type
        val tokenn = object : TypeToken<MutableList<com.example.potea.User.Person>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()
        val strt = cache.getString("Profile","")
        var user = mutableListOf<com.example.potea.User.Person>()
        user = gson.fromJson(strt,tokenn)

        var str = cache.getString("card", "")
        if (str != "") {
            list = gson.fromJson(str, type)
        }
        val a = Card_adapter(list, object : Card_adapter.delete {
            override fun del(a: Plant) {
                val myDialogFragment = MyDialogFragment(list, a)
                val manager = activity.supportFragmentManager
                myDialogFragment.show(manager, "")
                list = myDialogFragment.list
                cost(list)
                edit.putString("card", gson.toJson(list)).apply()
            }
        })
        if (list.isEmpty()) {
            bind.imageView17.visibility = View.VISIBLE
            bind.textView28.visibility = View.VISIBLE
            bind.textView29.visibility = View.VISIBLE
        }
        bind.recyclerView2.adapter = a
        cost(list)
        bind.button2.setOnClickListener {
            for (i in list){
                user[0].balance -= i.cost.toInt()
            }
            if (list.isNotEmpty()) {
                val st = cache.getString("history","")
                if (st != ""){
                    list.addAll(gson.fromJson(st,type))

                }
                Log.d("SHo", "oldingi : ${gson.fromJson<MutableList<Plant>>(st,type)} \n keyingi : ${list}")
                edit.putString("history", gson.toJson(list)).apply()

                list = mutableListOf()
                edit.putString("Profile", gson.toJson(user)).apply()
                edit.putString("card", gson.toJson(list)).apply()

                parentFragmentManager.beginTransaction().replace(R.id.changewindow, CartaFragment())
                    .commit()
            }else{
                Toast.makeText(requireContext(), "You must add item first !", Toast.LENGTH_SHORT).show()
            }
        }



        bind.imageView16.setOnClickListener{
            bind.imageView16.visibility = View.INVISIBLE
            bind.imageView15.visibility = View.INVISIBLE
            bind.serc.visibility = View.VISIBLE
        }

        var filter = mutableListOf<Plant>()
        bind.serC.addTextChangedListener {
            filter = mutableListOf()
            for (i in list){
                if (i.name.contains(it.toString())){
                    filter.add(i)
                }
                Log.d("SAG", "onCreateView: ${filter}")
            }
            bind.recyclerView2.adapter = Card_adapter(filter, object : Card_adapter.delete {
                override fun del(a: Plant) {
                    val myDialogFragment = MyDialogFragment(list, a)
                    val manager = activity.supportFragmentManager
                    myDialogFragment.show(manager, "")
                    list = myDialogFragment.list
                    cost(list)
                    edit.putString("card", gson.toJson(list)).apply()
                }
            })
        }

        return bind.root
    }

    @SuppressLint("SetTextI18n")
    fun cost(list: MutableList<Plant>) {
        var k = 0
        for (i in list) {
            k += i.cost.toInt()
        }
        bind.textView17.text = "$" + k.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}