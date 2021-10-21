package com.company.repathapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.databinding.ActivityMainBinding
import com.company.repathapp.model.User
import com.company.repathapp.viewmodel.LoginViewModel
import java.util.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        loginViewModel.getUser()!!.observe(this, { User ->

            if (!User.isEmailValid()){
                binding.emailText.setTextColor(0xfff00)
            }

            if (TextUtils.isEmpty(Objects.requireNonNull(User).getEmail())) {
                binding.emailText.error = "Enter an E-Mail Address"
                binding.emailText.requestFocus()
            } else if (!User.isEmailValid()) {
                binding.emailText.error = ("Enter a Valid E-mail Address")
                binding.emailText.requestFocus()

            } else if (TextUtils.isEmpty(Objects.requireNonNull(User).getPassword())) {
                binding.passwordText.error = ("Enter a Password")
                binding.passwordText.requestFocus()
            } else if (!User.isPasswordLengthGreaterThan5()) {
                binding.passwordText.error = ("Enter at least 6 Digit password")
                binding.passwordText.requestFocus()
            } else {
                binding.emailAnswer.text = "Success!"
                binding.passwordAnswer.text = "Success!"
            }
        })

        loginViewModel.getTextColor()!!.observe(this, { EmailText ->

            if (!EmailText.color()){
                binding.emailText.setTextColor(0xfff00)
            }

        })
}
}