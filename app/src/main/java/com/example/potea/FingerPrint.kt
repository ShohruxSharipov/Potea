package com.example.potea

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.potea.databinding.FragmentFingerPrintBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FingerPrint : Fragment() {
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
        val binding = FragmentFingerPrintBinding.inflate(inflater,container,false)
        binding.finger.visibility = View.VISIBLE
        binding.congratulation.visibility = View.GONE

        binding.skip.setOnClickListener {
            val handler = Handler()
            binding.finger.visibility = View.GONE
            binding.congratulation.visibility = View.VISIBLE

            handler.postDelayed(
                {findNavController().navigate(R.id.action_fingerPrint_to_bottomNav)}
            ,4000)

        }
        binding.contin.setOnClickListener {
            Toast.makeText(requireContext(), "404 ERROR", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FingerPrint().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}