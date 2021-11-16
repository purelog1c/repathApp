package com.company.repathapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.databinding.MapAttributesFragmentBinding
import com.company.repathapp.viewmodel.PotholeViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class PotholeFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: PotholeViewModel by viewModels()
    private lateinit var mapView : MapView
    private lateinit var mMap : GoogleMap
    private lateinit var observer: Unit
    private lateinit var binding: MapAttributesFragmentBinding

    companion object {
        fun newInstance() = PotholeFragment()
    }

   private lateinit var potholeViewModelProvider: PotholeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
/*
        mapView = requireView().findViewById(R.id.map) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)*/

        return inflater.inflate(R.layout.map_attributes_fragment, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        potholeViewModelProvider = ViewModelProvider(this)[PotholeViewModel::class.java]

        binding = MapAttributesFragmentBinding.inflate(this.layoutInflater)
        binding.lifecycleOwner = this
        binding.potholeViewModel = potholeViewModelProvider

        potholeViewModelProvider.getPotholeModel()!!.observe(this,{Pothole ->

            val tem = Pothole.getLocation()
/*
            Pothole.getUserID()
            Pothole.setLocation(location)*/
            Log.i("YES",tem.toString())
        })

    }

    fun tryout(){}


    fun setCoordinates(mLatitude:Double, mLongitude:Double) {
       // Log.i("YES","YES")
        val location = LatLng(mLatitude, mLongitude)
        potholeViewModelProvider.location = location
        potholeViewModelProvider.roadIcon = binding.HighRisk.background
        potholeViewModelProvider.CreatePothole()


    }


    fun upAnimation(){

        Log.i("Gola","Hola!!!")

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap!!
        mMap.setOnMapLongClickListener { Log.i("HI","Hi") }

        TODO("Not yet implemented")
    }
}