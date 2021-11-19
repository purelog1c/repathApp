package com.company.repathapp.viewmodel

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.company.repathapp.model.PotholeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.maps.model.LatLng

class PotholeViewModel : ViewModel(), RadioGroup.OnCheckedChangeListener {


    lateinit var location : LatLng
    lateinit var roadIcon : Drawable

    private val currentUser = FirebaseAuth.getInstance().currentUser?.uid

    private var mapAttributesLiveData: MutableLiveData<PotholeModel>? = null
    private var buttonID : MutableLiveData<Int>? = null


    fun getPotholeModel(): MutableLiveData<PotholeModel>? {

        if (mapAttributesLiveData == null){
            mapAttributesLiveData = MutableLiveData()
        }
        return mapAttributesLiveData
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId){
            checkedId -> {
                checkedId.also { buttonID?.value = it }
            }
        }
    }
    fun getPotholeTypeByID(): MutableLiveData<Int>? {
        buttonID = MutableLiveData()
        return buttonID
    }

    fun onConfirmed(view : View){
        val pothole = PotholeModel(location, roadIcon, currentUser)
        mapAttributesLiveData!!.value = pothole
    }




/*
    fun RadioButton(view : View){
        val checkedPotholeButton:RadioGroup =  (view as RadioGroup)
        buttonID = checkedPotholeButton.checkedRadioButtonId
        Log.i("BUTTON ID", buttonID.toString())
    }
*/

    fun CreatePothole(){
        val pothole = PotholeModel(location, roadIcon, currentUser)
        mapAttributesLiveData!!.value = pothole
    }
}


/*
    val selectedItem: LiveData<Layout> get() = mutableSelectedItem

    fun selectItem(layout: Layout) {
        mutableSelectedItem.value = layout
    }
*/
