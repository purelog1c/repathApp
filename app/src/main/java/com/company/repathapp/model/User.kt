package com.company.repathapp.model

import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.repathapp.R
import java.util.*

class User (private var email: String, private var password: String): BaseObservable() {


    fun isDataValid():Int{
        if (TextUtils.isEmpty(getEmail()))
            return  0
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            return  1
        else if (getPassword().length<7)
            return 2
        else
            return -1
    }

/*
    val color = MutableLiveData<String>()
    fun set(){
        if(Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
    }
    fun onEmailInput(){

        {
            color.value = "#000000"

        }else{
            color.value = "#FF0000"}
    }
*/



    @Bindable
    fun getPassword(): String{
        return password
    }
    @Bindable
    fun getEmail(): String{
        return email
    }



    fun setEmail(email: String){
        this.email=email
    }
    fun setPassword(password: String){
        this.password=password
    }
}