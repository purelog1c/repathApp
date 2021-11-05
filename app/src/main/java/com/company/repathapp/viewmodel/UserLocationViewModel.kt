package com.company.repathapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.UserLocationModel


class UserLocationViewModel: ViewModel() {

    var userLocationLiveData: MutableLiveData<UserLocationModel>? = null

    fun getUserLocation(): MutableLiveData<UserLocationModel>?{

        if (userLocationLiveData == null){
            userLocationLiveData = MutableLiveData()
        }
        return userLocationLiveData
    }

}