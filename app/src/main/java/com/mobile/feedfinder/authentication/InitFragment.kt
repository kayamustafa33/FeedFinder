package com.mobile.feedfinder.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mobile.feedfinder.databinding.FragmentInitBinding

class InitFragment : Fragment() {

    private lateinit var binding : FragmentInitBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInitBinding.inflate(layoutInflater)

        binding.goToSignInText.setOnClickListener {
            val action = InitFragmentDirections.actionInitFragmentToSignInFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.goToSignUpBtn.setOnClickListener {
            val action = InitFragmentDirections.actionInitFragmentToSignUpFragment()
            Navigation.findNavController(it).navigate(action)
        }

        return binding.root
    }

}