package com.mobile.feedfinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mobile.feedfinder.databinding.FragmentHomeBinding
import com.mobile.feedfinder.service.FirebaseImplementor
import com.mobile.feedfinder.util.showToast
import com.mobile.feedfinder.viewmodel.UserViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        // Testing of user data
        // Check Live data -> empty or not
        userViewModel.userData.value.let {
            if (it != null){
                showToast(requireContext(),"${it.email} -> It's working on Live Data")
            } else {
                // Current user data
                userViewModel.takeUserData(FirebaseImplementor().takeCurrentUserID()){ user ->
                    user?.let { networkData ->
                        showToast(requireContext(),"${networkData.email} -> It's working on Network Request")
                    }
                }
            }
        }

        return binding.root
    }

}