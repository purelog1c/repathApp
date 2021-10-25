package com.company.repathapp.viewmodel

import android.graphics.Color
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.User

class LoginViewModel: ViewModel()  {

        var emailAddress =  MutableLiveData<String>()
        var password = MutableLiveData<String>()

    private var userMutableLiveData: MutableLiveData<User>? = null


    fun getUser(): MutableLiveData<User>? {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
        return userMutableLiveData
    }

    private val textColor = MutableLiveData<Int>()

   private fun setColor(color: Int) {
        textColor.value = color
    }

    fun getColor(): MutableLiveData<Int> {
        return textColor
    }

    fun onEmailInput():Boolean{
        val loginUser = User(emailAddress.value, password.value)
        return if (!loginUser.isEmailValid()) {
            setColor(Color.RED)
            false
        }else{
            setColor(Color.BLACK)
            true
        }
    }


    fun onClick(view: View?) {
        val loginUser = User(emailAddress.value, password.value)
        userMutableLiveData!!.value = loginUser
    }
}
