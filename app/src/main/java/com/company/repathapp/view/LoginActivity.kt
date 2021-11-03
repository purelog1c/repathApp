package com.company.repathapp.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.databinding.ActivityLoginBinding
import com.company.repathapp.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        loginViewModel.getUser()!!.observe(this, { User ->

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
                binding.passwordText.error = ("Enter at least 6 digit password")
                binding.passwordText.requestFocus()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(User.getEmail().toString(),User.getPassword().toString())
                    .addOnCompleteListener{task->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@LoginActivity,
                                "You are successfully logged in",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }
                binding.emailAnswer.text = "Success!"
                binding.passwordAnswer.text = "Success!"
            }
        })
    }
}