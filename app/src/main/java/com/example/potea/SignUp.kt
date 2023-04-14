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
import androidx.navigation.fragment.findNavController
import com.example.potea.User.User
import com.example.potea.databinding.FragmentSignInBinding
import com.example.potea.databinding.FragmentSignUpBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUp.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp : Fragment() {
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
        val binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()

        val activity : AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()
        var list = mutableListOf<User>()





        binding.textView8.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_signIn)
        }

        binding.next1.setOnClickListener {
            val str = cache.getString("user","")
            list = gson.fromJson(str,type)
            val a = list[0]
            Log.d("TAG", "onCreateView: ${a.login}  ${a.pasword}")

                if (a.login == binding.log.text.toString() && a.pasword == binding.pass.text.toString() ){
                    Toast.makeText(requireContext(), "True", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(), "false", Toast.LENGTH_SHORT).show()
                }
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
         * @return A new instance of fragment SignUp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUp().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}