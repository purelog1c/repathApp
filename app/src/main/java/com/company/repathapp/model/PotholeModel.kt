package com.company.repathapp.model

import com.google.android.gms.maps.model.LatLng

class PotholeModel ( private var potholePosition: LatLng?, private var UID : String?, private var importanceCount: Int?) {


    fun getLocation():LatLng?{
        return potholePosition
    }

    fun getImportanceCount(): Int? {
        return importanceCount
    }

    fun setImportanceCount(count:Int){
        importanceCount = count
    }

    fun setLocation(latLng: LatLng){
        this.potholePosition = latLng
    }

    fun getRoadIcon():LatLng?{
        return potholePosition
    }

    fun getUserID():String?{
        return UID
    }


}