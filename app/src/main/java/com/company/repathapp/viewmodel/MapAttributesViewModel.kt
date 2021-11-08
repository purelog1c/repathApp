package com.company.repathapp.viewmodel

import android.media.Image
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.MapAttributesModel
import com.google.firebase.auth.FirebaseAuth
import com.google.type.LatLng

class MapAttributesViewModel : ViewModel() {

    var location = MutableLiveData<LatLng>()
    var roadIcon = MutableLiveData<Image>()
    private val currentUser = FirebaseAuth.getInstance().currentUser?.uid

    var mapAttributesLiveData: MutableLiveData<MapAttributesModel>? = null

    fun getMapAttribute(): MutableLiveData<MapAttributesModel>? {

        if (mapAttributesLiveData == null){
            mapAttributesLiveData = MutableLiveData()
        }
        return mapAttributesLiveData
    }


    fun setMapAttribute(mLatitude:Double, mLongitude:Double){
        val mLatitude = location.value?.latitude
        val mLongitude = location.value?.longitude

        val mapAttributes = MapAttributesModel(location.value, roadIcon.value, currentUser)
        mapAttributesLiveData!!.value = mapAttributes
    }
}