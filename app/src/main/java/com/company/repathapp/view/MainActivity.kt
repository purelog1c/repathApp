package com.company.repathapp.view

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.databinding.ActivityMainBinding
import com.company.repathapp.viewmodel.LoginViewModel
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        loginViewModel.getUser()!!.observe(this, { User ->

            if (TextUtils.isEmpty(Objects.requireNonNull(User).getEmail())) {
                binding.emailText.error = "Enter an E-Mail Address"
                binding.emailText.requestFocus()
            } else if (!User.isEmailValid()) {
                binding.emailText.error = ("Enter a Valid E-mail Address")
                binding.emailText.setTextColor(Color.RED)
                binding.emailText.requestFocus()

            } else if (TextUtils.isEmpty(Objects.requireNonNull(User).getPassword())) {
                binding.passwordText.error = ("Enter a Password")
                binding.passwordText.requestFocus()
            } else if (!User.isPasswordLengthGreaterThan5()) {
                binding.passwordText.error = ("Enter at least 6 digit password")
                binding.passwordText.requestFocus()
                binding.passwordText.setTextColor(Color.RED)
            } else {
                binding.emailAnswer.text = "Success!"
                binding.passwordAnswer.text = "Success!"
            }
        })
    }
}