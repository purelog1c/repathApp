package com.company.repathapp.viewmodel


import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.UserLoginModel
import com.company.repathapp.view.LoginActivity
import com.company.repathapp.view.RegistrationActivity

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

    fun onSignUpClicked(view: View?){
        val context = view?.context
        val intent = Intent(context, RegistrationActivity::class.java)
        context?.startActivity(intent)
    }

    // CHECK USER ON CLICK
    fun onLoginClicked(view: View?) {
        val loginUser = UserLoginModel(emailAddress.value, password.value)
        // SET USER
        userLoginModelMutableLiveData!!.value = loginUser
    }
}
