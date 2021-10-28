package com.company.repathapp.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.databinding.ActivityLoginBinding
import com.company.repathapp.utils.PasswordStrengthCalculator
import com.company.repathapp.viewmodel.UserRegistrationViewModel
import com.company.repathapp.databinding.ActivityRegisterBinding
import com.company.repathapp.utils.StrengthLevel


class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var color: Int = R.color.weak
        val passwordStrengthCalculator = PasswordStrengthCalculator()

        super.onCreate(savedInstanceState)

        val userRegistrationViewModel = ViewModelProvider(this).get(UserRegistrationViewModel::class.java)
        val registerBinding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this,R.layout.activity_register)

        registerBinding.lifecycleOwner = this
        registerBinding.userRegistrationViewModel = userRegistrationViewModel
        registerBinding.passwordInput.addTextChangedListener(passwordStrengthCalculator)
        val strength = registerBinding.passwordInput.addTextChangedListener(passwordStrengthCalculator)

        userRegistrationViewModel.getRegisteringUser()!!.observe(this, {User ->

            if(!User.isValidNameOrSurname()){
                registerBinding.editTextPersonName.error = "Enter a valid Name"
            }

        })

        fun displayStrengthLevel(strengthLevel: StrengthLevel) {

            //registerBinding.strengthLevelTxt.text = strengthLevel.name
            registerBinding.strengthLevelTxt.setTextColor(Color.WHITE)
            registerBinding.strengthLevelTxt.text = strengthLevel.name
            Log.i("strength level", strengthLevel.name)
            //registerBinding.strengthLevelTxt.setTextColor(ContextCompat.getColor(this, color))
            registerBinding.strengthLevelTxt.setBackgroundColor(ContextCompat.getColor(this, color))
        }
        passwordStrengthCalculator.strengthColor.observe(this, {strengthColor ->
            color = strengthColor
        })
        passwordStrengthCalculator.strengthLevel.observe(this, {strengthLevel ->
            displayStrengthLevel(strengthLevel)
        })

    }
}