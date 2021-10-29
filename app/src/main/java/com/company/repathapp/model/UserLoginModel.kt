package com.company.repathapp.model

import android.util.Patterns

class UserLoginModel(private var email: String?, private var password: String?) {


        fun setEmail(email: String?){
            this.email = email.toString()
        }

        fun setPassword(password: String?){
            this.password = password.toString()
        }

        fun getEmail(): String? {
            return email
        }

        fun getPassword(): String? {
            return password
        }



    fun isPasswordLengthGreaterThan5(): Boolean {
        val passwordLength: Int? = getPassword()?.length
        return passwordLength != null && passwordLength > 5
    }

    fun isEmailValid(): Boolean{
      return Patterns.EMAIL_ADDRESS.matcher(getEmail().toString()).matches()
    }

}