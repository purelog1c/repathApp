package com.company.repathapp.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.databinding.ActivityMapsBinding
import com.company.repathapp.viewmodel.MapAttributesViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnSuccessListener<Location> {

    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*        val mapAttributeViewModel = ViewModelProvider(this)[MapAttributesViewModel::class.java]
        val mapAttributeBinding = DataBindingUtil.setContentView<ActivityMapsBinding>(this,R.layout.activity_maps)

        mapAttributeBinding.lifecycleOwner = this
        mapAttributeBinding.map = mapAttributeViewModel*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MapAttributesFragment>(R.id.map)
            }
        }

      //  supportFragmentManager.beginTransaction().replace(R.layout.map_attributes_fragment,)






        /*val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel*/
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    /*
    fun getMap(): GoogleMap {
        return mMap
    }
*/
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED/* &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED*/){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
            return
        }
        setupMap()
        googleMap.setOnMapClickListener {
        }
    }

    private fun setupMap()
    {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.lastLocation.addOnSuccessListener(this, this)


    }

    override fun onSuccess(location: Location?) {
        if(location != null){
            val mLatitude = location.latitude
            val mLongitude = location.longitude
            val sydney = LatLng(mLatitude, mLongitude)
            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                setupMap()
            }
            else
            {
                // Tell user that app won't work properly, because permission isn't granted
            }
        }
    }

}