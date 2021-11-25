package com.company.repathapp.viewmodel

import android.util.Log
import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.repathapp.Repo.PotholeLocation
import com.company.repathapp.model.PotholeModel
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PotholeViewModel : ViewModel(), RadioGroup.OnCheckedChangeListener {

    //MODEL RELATED DATA
    private var potholeLocation: MutableLiveData<LatLng>? = null
    private var importanceCount: MutableLiveData<Int>? = null
    private val currentUser = FirebaseAuth.getInstance().currentUser?.uid

    private var mapAttributes = MutableLiveData<PotholeModel>()
    val _mapAttributes: LiveData<PotholeModel>
    get() = mapAttributes

    //ATTRIBUTE RELATED DATA
    internal var jsonResponse = MutableLiveData<String>() //MutableLiveData<String>
    public val _jsonResponse : LiveData<String>
        get() = jsonResponse


    private var buttonID = MutableLiveData<Int>()
    val _buttonID : LiveData<Int>
        get() = buttonID

    var isOver: MutableLiveData<Boolean>? = null


    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            checkedId -> {
                checkedId.also {
                    buttonID?.postValue(it)
                }
            }
        }
    }

    fun sendGetClosesRoad(location: LatLng) {

    }

    private fun createPotholeFromJson(response: MutableLiveData<String>?) {
        if (response?.value?.length!! > 5) {
            val gson = Gson()
            val potholeModel = gson.fromJson(response.value, PotholeLocation::class.java)

            val mLatitude = potholeModel.snappedPoints[0].location.latitude
            val mLongitude = potholeModel.snappedPoints[0].location.longitude

            val latLng = LatLng(mLatitude,mLongitude)
            potholeLocation?.value = LatLng(mLatitude, mLongitude)

            Log.w("LOCATION FROM API", latLng.toString())

        }
        else Log.i("READABLE?", "NOT READABLE!!!!!")
    }


    fun onConfirmed(view: View) {
        importanceCount?.value = 0
        val pothole = PotholeModel(potholeLocation?.value, currentUser, importanceCount?.value)
        mapAttributes.postValue(pothole)
    }
}