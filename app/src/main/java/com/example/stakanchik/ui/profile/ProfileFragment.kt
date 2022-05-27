package com.example.stakanchik.ui.profile

import android.os.Bundle
import android.view.View
import com.example.stakanchik.databinding.FragmentProfileBinding
import com.example.stakanchik.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<ProfileViewModel, FragmentProfileBinding>(
    ProfileViewModel::class.java,
    { FragmentProfileBinding.inflate(it) }
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}