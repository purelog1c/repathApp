package com.company.repathapp.viewmodel

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.User
import org.w3c.dom.Text

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

    private var textColor: MutableLiveData<EditText>? = null

    fun getTextColor(): MutableLiveData<EditText>?{
        if (textColor == null) {
            textColor = MutableLiveData()
        }
        return textColor
    }






    fun onClick(view: View?) {
        val loginUser = User(emailAddress.value, password.value)
        userMutableLiveData!!.value = loginUser
    }
}
