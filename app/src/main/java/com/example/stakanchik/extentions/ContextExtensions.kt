package com.example.stakanchik.extentions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String, length: Int? = Toast.LENGTH_LONG){
    Toast.makeText(requireContext(),message, length!!).show()
}