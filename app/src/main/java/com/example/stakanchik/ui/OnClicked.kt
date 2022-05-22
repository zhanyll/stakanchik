package com.example.stakanchik.ui

import androidx.fragment.app.Fragment

interface OnClicked {
    fun onClickOpenFragment(fragment: Fragment, addToBackStack: Boolean? = true) {}
}