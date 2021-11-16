package com.company.repathapp.viewmodel

import android.graphics.drawable.Drawable
import android.media.Image
import android.view.translation.TranslationContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.repathapp.model.PotholeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.maps.model.LatLng

class PotholeViewModel : ViewModel() {

    lateinit var location : LatLng
    lateinit var roadIcon : Drawable
    val currentUser = FirebaseAuth.getInstance().currentUser?.uid

    var mutableLayoutItem = MutableLiveData<TranslationContext>()

    var mapAttributesLiveData: MutableLiveData<PotholeModel>? = null

    fun getPotholeModel(): MutableLiveData<PotholeModel>? {

        if (mapAttributesLiveData == null){
            mapAttributesLiveData = MutableLiveData()
        }
        return mapAttributesLiveData
    }


    fun CreatePothole(){
        val pothole = PotholeModel(location, roadIcon, currentUser)
        mapAttributesLiveData!!.value = pothole
    }

/*
    val selectedItem: LiveData<Layout> get() = mutableSelectedItem

    fun selectItem(layout: Layout) {
        mutableSelectedItem.value = layout
    }
*/

}