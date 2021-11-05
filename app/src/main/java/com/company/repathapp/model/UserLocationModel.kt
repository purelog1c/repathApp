package com.company.repathapp.model

import com.company.repathapp.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.type.LatLng

class UserLocationModel(private var UID: String?,private var userLocation: LatLng?) {

    fun getUID():String?{
        return UID
    }

    fun isValidUser(UID: String?):Boolean{
        return UID == Firebase.auth.currentUser!!.uid
    }

    fun getUserLocation():LatLng?{
        return userLocation
    }

}