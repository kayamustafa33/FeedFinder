package com.mobile.feedfinder.authentication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mobile.feedfinder.databinding.FragmentSignInBinding
import com.mobile.feedfinder.model.User
import com.mobile.feedfinder.util.showToast
import com.mobile.feedfinder.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

class SignInFragment : Fragment() {

    private lateinit var binding : FragmentSignInBinding
    private val userViewModel: UserViewModel by viewModels()

    private var email : String? = null
    private var password : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)

        login()

        return binding.root
    }

    private fun login(){
        binding.signInBtn.setOnClickListener {
            email = binding.emailEditText.text.toString()
            password = binding.passwordEditText.text.toString()

            clearAttr()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                // Display message
                showToast(requireContext(),"Fill in the fields!")
            } else {
                val user = User(email!!,password!!)
                userViewModel.loginUser(requireContext(),user)
            }
        }
    }

    private fun clearAttr(){
        binding.emailEditText.text.clear()
        binding.passwordEditText.text.clear()
    }

}