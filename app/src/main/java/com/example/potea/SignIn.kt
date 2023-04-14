package com.example.potea

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.potea.User.User
import com.example.potea.databinding.FragmentSignInBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignIn : Fragment() {
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
        val binding = FragmentSignInBinding.inflate(inflater,container,false)

        binding.textView8.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_signUp)
        }

        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()

        val activity :  AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", MODE_PRIVATE)
        val edit = cache.edit()
        val list = mutableListOf<User>()

        binding.next1.setOnClickListener {
            val login : String = binding.login.text.toString()
            val pasword : String = binding.password.text.toString()
            val user = User(login, pasword)
            list.add(user)
            val u1 = gson.toJson(list)
            edit.putString("user",u1).apply()
            findNavController().navigate(R.id.action_signIn_to_signUp)

        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignIn().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}