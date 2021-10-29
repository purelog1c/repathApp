package com.company.repathapp.viewmodel
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.UserRegistrationModel
import com.company.repathapp.utils.PasswordStrengthCalculator

class UserRegistrationViewModel :ViewModel() {


    var name = MutableLiveData<String>()
    var surname = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var passwordVerification = MutableLiveData<String>()


    var userRegistrationLiveData: MutableLiveData<UserRegistrationModel>? = null

    fun getRegisteringUser(): MutableLiveData<UserRegistrationModel>?{

        if (userRegistrationLiveData == null){
            userRegistrationLiveData = MutableLiveData()
        }
        return userRegistrationLiveData
    }

    fun registerOnClick(v: View){
        val registeringUser = UserRegistrationModel(name.value,surname.value,email.value,password.value)
        userRegistrationLiveData!!.value = registeringUser
    }
}