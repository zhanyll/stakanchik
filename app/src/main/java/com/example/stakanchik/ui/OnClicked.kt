package com.example.stakanchik.ui

import androidx.fragment.app.Fragment

interface OnClicked {
    fun onMain(fragment: Fragment, addToBackStack: Boolean? = true) {}
}