package com.company.repathapp.view

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.company.repathapp.R
import com.company.repathapp.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.squareup.okhttp.OkHttpClient


internal inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    // Chained
    beginTransaction().func().commit()
}

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnSuccessListener<Location> {

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mapAttributesFragment: Unit
    private var homeFragment: PotholeFragment? = null

    private fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag : String){
        supportFragmentManager.inTransaction { add(frameId, fragment, tag) }
    }
    private fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, tag: String) {
        supportFragmentManager.inTransaction{replace(frameId, fragment , tag)}
    }
    private fun AppCompatActivity.removeFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction{remove(fragment)}
    }

    private fun getPotholeFragment() : PotholeFragment {
       return (supportFragmentManager.findFragmentByTag("mapAtb") as PotholeFragment)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapAttributesFragment = addFragment(PotholeFragment(),R.id.map,"mapAtb")
       // getMapFragment = (mapAttributesFragment as MapAttributesFragment)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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



        //addFragment(MapAttributesFragment(), R.id.map)
        googleMap.setOnMapLongClickListener{ location -> getLocation(location)
        sendGetClosestRoad(location)

            val homeFrag =  getPotholeFragment()
            homeFrag.upAnimation()
            //homeFrag.setCoordinates(12.3,45.2)
/*            val fragmentA = supportFragmentManager.findFragmentByTag("mapAtb")
            //supportFragmentManager.findFragmentById(MapAttributesFragment().id)

            if(fragmentA == null){


              //   = (mapAttributesFragment as MapAttributesFragment).upAnimation()
            }else{
                Log.i("EXISTS!","FRAGMENT EXISTS IN THE SCENE")
            }*///binding.root.getLocationOnScreen()
        }
    }





    private fun View.getLocationOnScreen(): Point {
        val location = IntArray(2)
        this.getLocationOnScreen(location)
        Log.w("Location", Point(location[0],location[1]).toString())
        return Point(location[0],location[1])
    }

    private fun getLocation(point:LatLng){
        Toast.makeText(this,
            point.latitude.toString() + ", " + point.longitude,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun sendGetClosestRoad(location: LatLng){

        val roadLat = location.latitude.toString()
        val roadLong = location.longitude.toString()
        var full = roadLat.plus(",").plus(roadLong)

        Log.i("LatLng",full.toString())
        val client = OkHttpClient()



/*        GlobalScope.launch(Dispatchers.IO) {

            val request = Request.Builder()
                .url("https://roads.googleapis.com/v1/nearestRoads?points=$full&key=AIzaSyD9y9En0zDS3fxSll0-CL8hFBzhH9lNLqg")
                .method("GET", null)
                .build()

            val response = client.newCall(request).execute()
            if(response.isSuccessful){
                println(response.body()?.string())



                Log.i("Success","Successful")
            }else{Log.i("NoSuccess","UnSuccessful")}
        }*/

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
