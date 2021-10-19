package com.company.repathapp.viewmodel
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.company.repathapp.Interface.LoginResultCallBacks
import com.company.repathapp.model.User


class LoginViewModel(private val listener : LoginResultCallBacks ) :ViewModel() {

    private val user:User = User("","")


    val emailTextWatcher: TextWatcher
        get()= object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                user.setEmail(s.toString())
                Log.i("Email","Here I am Email")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }


    //create function to set Password after user finish enter text
    val passwordTextWatcher:TextWatcher
        get()= object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                user.setPassword(s.toString())
                Log.i("Pass","Here I am Pass")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }


    fun onLoginClicked(v:View){
        var loginCode:Int = user.isDataValid()
        if (loginCode == 0)
            listener.onError("Email cannot be empty ")
        else if (loginCode == 1)
            listener.onError("It is not a valid Email")
        else if (loginCode == 2)
            listener.onError("Password length should be more than 6 characters")
        else
            listener.onSuccess("Login Successful")
    }
}