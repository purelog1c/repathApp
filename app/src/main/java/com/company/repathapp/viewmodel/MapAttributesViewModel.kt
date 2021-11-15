package com.company.repathapp.viewmodel

import android.content.ClipData.Item
import android.media.Image
import android.text.Layout
import android.view.translation.TranslationContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.MapAttributesModel
import com.google.firebase.auth.FirebaseAuth
import com.google.type.LatLng

class MapAttributesViewModel : ViewModel() {

    var location = MutableLiveData<LatLng>()
    var roadIcon = MutableLiveData<Image>()
    private val currentUser = FirebaseAuth.getInstance().currentUser?.uid
    var mutableLayoutItem = MutableLiveData<TranslationContext>()

    var mapAttributesLiveData: MutableLiveData<MapAttributesModel>? = null

    fun getMapAttribute(): MutableLiveData<MapAttributesModel>? {

        if (mapAttributesLiveData == null){
            mapAttributesLiveData = MutableLiveData()
        }
        return mapAttributesLiveData
    }

/*
    val selectedItem: LiveData<Layout> get() = mutableSelectedItem

    fun selectItem(layout: Layout) {
        mutableSelectedItem.value = layout
    }
*/

    fun setMapAttribute(mLatitude:Double, mLongitude:Double){
        val mLatitude = location.value?.latitude
        val mLongitude = location.value?.longitude

        val mapAttributes = MapAttributesModel(location.value, roadIcon.value, currentUser)
        mapAttributesLiveData!!.value = mapAttributes
    }
}