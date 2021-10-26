package com.company.repathapp.viewmodel


import android.graphics.Color
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.User

class LoginViewModel: ViewModel()  {

    // LOGIN PARAMETERS FOR VIEW
    var emailAddress =  MutableLiveData<String>()
    var password = MutableLiveData<String>()

    // CREATE USER
    private var userMutableLiveData: MutableLiveData<User>? = null

    // SETTING PASSWORD COLOR
    private val passColor = MutableLiveData<Int>()
    private fun setPassColor(passwordColor: Int) {
        passColor.value = passwordColor
    }
    fun getPassColor(): MutableLiveData<Int> {
        return passColor
    }

    // SETTING EMAIL COLOR
    private val emailColor = MutableLiveData<Int>()
    private fun setEmailColor(color: Int) {
        emailColor.value = color
    }
    fun getEmailColor(): MutableLiveData<Int> {
        return emailColor
    }
    fun onEmailChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            setEmailColor(Color.BLACK)
    }
    fun onPasswordChanged (s: CharSequence, start: Int, before: Int, count: Int) {
            setPassColor(Color.BLACK)
    }

    //GET USER
    fun getUser(): MutableLiveData<User>? {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
        return userMutableLiveData
    }

    // CHECK USER ON CLICK
    fun onClick(view: View?) {
        val loginUser = User(emailAddress.value, password.value)
        // SET USER
        userMutableLiveData!!.value = loginUser
    }
}
