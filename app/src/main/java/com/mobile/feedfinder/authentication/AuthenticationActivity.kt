package com.mobile.feedfinder.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.feedfinder.databinding.ActivityAuthenticationBinding
import com.mobile.feedfinder.service.FirebaseImplementor
import com.mobile.feedfinder.util.goActivityWithFlags
import com.mobile.feedfinder.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (FirebaseImplementor().isLogin()){
            goActivityWithFlags(this,MainActivity::class.java)
        }

    }
}