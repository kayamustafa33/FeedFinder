package com.mobile.feedfinder.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mobile.feedfinder.databinding.FragmentSignUpBinding
import com.mobile.feedfinder.model.User
import com.mobile.feedfinder.util.goActivityWithFlags
import com.mobile.feedfinder.view.MainActivity
import com.mobile.feedfinder.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignUpBinding
    private var email : String? = null
    private var password : String? = null
    private var confirmPassword : String? = null
    private var currentContext : Context? = null

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        currentContext = binding.root.context
        createUser()

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        return binding.root
    }

    private fun createUser(){
        binding.signUpBtn.setOnClickListener {
            email = binding.emailEditText.text.toString()
            password = binding.passwordEditText.text.toString()
            confirmPassword = binding.confirmPasswordEditText.text.toString()

            clearAttr()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
                Toast.makeText(currentContext,"Fill in the field!",Toast.LENGTH_SHORT).show()
            } else {
                if (!TextUtils.equals(password,confirmPassword)){
                    Toast.makeText(currentContext,"Passwords doesn't matches!",Toast.LENGTH_SHORT).show()
                } else {
                    val user = User(email!!,password!!)
                    currentContext?.let {
                        userViewModel.createUser(it,user)
                    }
                }
            }
        }
    }

    private fun clearAttr(){
        binding.emailEditText.text.clear()
        binding.passwordEditText.text.clear()
        binding.confirmPasswordEditText.text.clear()
    }

}