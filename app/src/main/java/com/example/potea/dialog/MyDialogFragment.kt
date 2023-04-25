package com.example.potea.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.potea.Plant.Plant
import com.example.potea.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyDialogFragment(var list:MutableList<Plant>,var plant:Plant) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setTitle("Warn")
                .setMessage("Do you want to delete ?")
                .setPositiveButton("Yes") { dialog, id ->
                    list.remove(plant)
                }
                .setNegativeButton("No"){ dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}