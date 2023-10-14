package com.mobile.feedfinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.feedfinder.R
import com.mobile.feedfinder.databinding.FragmentShareAnimalLocationBinding

class ShareAnimalLocationFragment : Fragment() {

    private lateinit var binding : FragmentShareAnimalLocationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShareAnimalLocationBinding.inflate(layoutInflater)


        return binding.root
    }

}