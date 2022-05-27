package com.example.stakanchik.ui

import android.app.Activity
import androidx.fragment.app.Fragment

interface OnClicked {
    fun onClickOpenFragment(fragment: Fragment, addToBackStack: Boolean? = true) {}

    fun onClickOpenArticle(activity: Activity, addToBackStack: Boolean? = true) {}
}