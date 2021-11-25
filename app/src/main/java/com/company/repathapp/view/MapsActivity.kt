package com.company.repathapp.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.company.repathapp.R
import com.company.repathapp.databinding.ActivityMapsBinding
import com.company.repathapp.viewmodel.PotholeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


internal inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    // Chained
    beginTransaction().func().commit()
}

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding


    private lateinit var mapsFragment: Unit
    private lateinit var potholeFragment: Unit

    private val potholeViewModel: PotholeViewModel by viewModels()

    private fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag: String) {
        supportFragmentManager.inTransaction { add(frameId, fragment, tag) }
    }

    private fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, tag: String) {
        supportFragmentManager.inTransaction { replace(frameId, fragment, tag) }
    }

    private fun AppCompatActivity.removeFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction { remove(fragment) }
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapsFragment =  addFragment(MapsFragment(), R.id.map, "mapFrg")
        potholeFragment =  addFragment(PotholeFragment(), R.id.map, "pthFrg")

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setupMap()
    }

    private fun setupMap() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupMap()
            } else {
                // Tell user that app won't work properly, because permission isn't granted
            }
        }
    }

}
