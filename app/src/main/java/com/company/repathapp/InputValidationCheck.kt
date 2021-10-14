package com.company.repathapp

import android.graphics.Color
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText


fun isValidEmail(email: String): Boolean{

    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@JvmName("isValidEmail01")
fun String.isValidEmail() = !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun isValidEmailInput(emailEditText: EditText): Boolean{

    return if (!isValidEmail(emailEditText.text.toString())){
        emailEditText.setTextColor(Color.parseColor("red"))

        false
    }else{
        emailEditText.setTextColor(Color.parseColor("#757575"))
        true
    }
}