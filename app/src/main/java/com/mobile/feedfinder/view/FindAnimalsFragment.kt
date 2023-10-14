package com.mobile.feedfinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.feedfinder.databinding.FragmentFindAnimalsBinding

class FindAnimalsFragment : Fragment() {

    private lateinit var binding: FragmentFindAnimalsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFindAnimalsBinding.inflate(layoutInflater)


        return binding.root
    }

}