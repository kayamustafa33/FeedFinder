package com.mobile.feedfinder.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.feedfinder.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

class SignInFragment : Fragment() {

    private lateinit var binding : FragmentSignInBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)


        return binding.root
    }


}