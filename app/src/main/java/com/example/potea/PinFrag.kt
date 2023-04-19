package com.example.potea

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.potea.databinding.FragmentPinBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PinFrag : Fragment() {
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
        val binding = FragmentPinBinding.inflate(inflater,container,false)
        val activity : AppCompatActivity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()

        var pin = cache.getString("code","")


        binding.next1.setOnClickListener {
            if (pin == ""){
                cache.edit().putString("code",binding.firstPinView.text.toString()).commit()
//                Toast.makeText(requireContext(), "SERROR", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_pinFrag_to_fingerPrint)
            }else if (pin == binding.firstPinView.text.toString()){
                findNavController().navigate(R.id.action_pinFrag_to_bottomNav)
            }else Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PinFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}