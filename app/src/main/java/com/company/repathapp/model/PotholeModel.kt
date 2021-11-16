package com.company.repathapp.model

import android.graphics.drawable.Drawable
import android.media.Image
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.android.gms.maps.model.LatLng

class PotholeModel (private var location : LatLng?, private var roadIcon: Drawable?, private var UID : String?) {


    fun getLocation():LatLng?{
        return location
    }

    fun setLocation(latLng: LatLng){
        this.location = latLng
    }

    fun getRoadIcon():Drawable?{
        return roadIcon
    }

    fun getUserID():String?{
        return UID
    }


}