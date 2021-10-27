package com.company.repathapp.viewmodel


import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.UserLoginModel

class LoginViewModel: ViewModel()  {

    // LOGIN PARAMETERS FOR VIEW
    var emailAddress =  MutableLiveData<String>()
    var password = MutableLiveData<String>()

    // CREATE USER
    private var userLoginModelMutableLiveData: MutableLiveData<UserLoginModel>? = null

    //GET USER
    fun getUser(): MutableLiveData<UserLoginModel>? {
        if (userLoginModelMutableLiveData == null) {
            userLoginModelMutableLiveData = MutableLiveData()
        }
        return userLoginModelMutableLiveData
    }

    // CHECK USER ON CLICK
    fun onClick(view: View?) {
        val loginUser = UserLoginModel(emailAddress.value, password.value)
        // SET USER
        userLoginModelMutableLiveData!!.value = loginUser
    }
}
