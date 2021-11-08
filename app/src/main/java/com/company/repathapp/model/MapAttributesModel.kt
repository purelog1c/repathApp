package com.company.repathapp.model

import android.media.Image
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.type.LatLng

class MapAttributesModel (private var location : LatLng?, private var roadIcon: Image?, private var UID : String?) {


    fun getLocation():LatLng?{
        return location
    }

    fun getRoadIcon():Image?{
        return roadIcon
    }

    fun getUserID():String?{
        return UID
    }


}