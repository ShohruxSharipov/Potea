package com.example.potea

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.potea.Plant.Plant
import com.example.potea.databinding.FragmentMyWishlistBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyWishlist.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyWishlist : Fragment() {
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
        val binding = FragmentMyWishlistBinding.inflate(inflater,container,false)


        val list = arguments?.getSerializable("wishlist") as MutableList<Plant>

//        Log.d("TAG", "onCreateView: ${list.toString()}")
        val adapter = com.example.potea.adapter.Adapter(list, requireContext(),object : com.example.potea.adapter.Adapter.ItemClick{
            override fun OnItemClick(plant: Plant) {
                val item = bundleOf("item" to plant)
                findNavController().navigate(R.id.action_home2_to_itemFragment, item)
            }})
        binding.recyclerView.adapter = adapter



        binding.back.setOnClickListener {
            val activity : AppCompatActivity = activity as AppCompatActivity
            activity.onBackPressedDispatcher.onBackPressed()
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
         * @return A new instance of fragment MyWishlist.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyWishlist().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}