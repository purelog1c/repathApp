package com.company.repathapp.model

import android.util.Patterns
import com.company.repathapp.utils.PasswordStrengthCalculator

class UserRegistrationModel(private var name: String?, private var surname: String?, private var email: String?, private var password: String?) {



    private fun getName(): String? {
        return name
    }

    fun getSurname(): String?{
        return surname
    }

    fun getEmail():String? {
        return email
    }

    fun getPassword():String?{
        return password
    }


    fun isValidEmail():Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(getEmail().toString()).matches()
    }

    fun isPasswordLengthGreaterThan5(): Boolean {
        val passwordLength: Int? = getPassword()?.length
        return passwordLength != null && passwordLength > 5
    }



    fun isValidNameOrSurname():Boolean{
        return getName()?.toRegex()?.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?") == true
    }



}