package com.company.repathapp.viewmodel

import android.content.ClipData
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.company.repathapp.Interface.LoginResultCallBacks
import com.company.repathapp.model.User

class LoginViewModel(private val listener: LoginResultCallBacks): ViewModel() {
    private val user:User = User("","")


    //create function to set Email after user finish enter text
    val emailTextWatcher: TextWatcher
        get()= object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                user.setEmail(s.toString())



            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                user.onEmailInput()

            }

        }


    //create function to set Password after user finish enter text
    val passwordTextWatcher:TextWatcher
        get()= object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                user.setPassword(s.toString())
                user.onEmailInput()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }


    //create function to process Login Button clicked
    fun onLoginClicked(v: View){
        var loginCode:Int = user.isDataValid()
        if (loginCode == 0)
            listener.onError("Email address cannot be empty!")
        else if (loginCode == 1)
            listener.onError("Not a valid email")
        else if (loginCode == 2)
            listener.onError("Password should be more than 6 characters")
        else
            listener.onSuccess("Accessed!")
    }


}