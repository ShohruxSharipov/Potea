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
import androidx.navigation.fragment.findNavController
import com.example.potea.User.Person
import com.example.potea.User.User
import com.example.potea.databinding.FragmentProfileSettingBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileSetting.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileSetting : Fragment() {
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
        val binding = FragmentProfileSettingBinding.inflate(inflater,container,false)
        val activity : AppCompatActivity = activity as AppCompatActivity
        val type = object : TypeToken<List<Person>>() {}.type
        val gson = Gson()
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()

        val str = cache.getString("Profile","")

        var list = mutableListOf<Person>()
        list = gson.fromJson(str, type)
        Log.d("PAG", "LIST: ${list.toString()}")
        val user = list[0]

        binding.update.setOnClickListener {
            user.first_name = binding.fullname.text.toString()
            user.last_name = binding.nickname.text.toString()
            user.number = binding.email.text.toString()
            user.birth = binding.date.text.toString()

            list[0] = user
            edit.putString("Profile",gson.toJson(list)).apply()
            Toast.makeText(requireContext(), "Succesful", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileSetting_to_home2)
            Log.d("PAG", "LIST 222: ${list.toString()}")
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
         * @return A new instance of fragment ProfileSetting.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileSetting().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}