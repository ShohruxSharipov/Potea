package com.example.potea

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.potea.User.Person
import com.example.potea.User.User
import com.example.potea.databinding.FragmentFillProfileBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FillProfile : Fragment() {

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
        var binding = FragmentFillProfileBinding.inflate(inflater, container, false)
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()
        val activity: AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()
        var user_list = mutableListOf<Person>()


        val list = mutableListOf<String>("Gender", "Male", "Female")
        var adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, list)
        binding.dynamic.adapter = adapter
        var state =
            mutableListOf<String>("+998", "+1", "+167", "+8", "+23", "+265", "+1003", "+93", "+999")
        var a = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, state)
        binding.spinner.adapter = a

        binding.next1.setOnClickListener {
            if (binding.fullname.text.isNullOrBlank()
                or binding.nickname.text.isNullOrBlank()
                or binding.date.text.isNullOrBlank()
                or binding.number.text.isNullOrBlank()
            ) {
                Toast.makeText(requireContext(), "FILL ALL !!", Toast.LENGTH_SHORT).show()
            } else {
                val user = Person(
                    binding.fullname.text.toString(),
                    binding.nickname.text.toString(),
                    binding.date.text.toString(),
                    binding.email.text.toString(),
                    binding.number.text.toString(),
                    true,
                    999
                )
                user_list.add(user)
                val p = gson.toJson(user_list)
                edit.putString("Profile", p).apply()
                findNavController().navigate(R.id.action_fillProfile_to_pinFrag)
            }
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FillProfile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}