package com.company.repathapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.company.repathapp.R
import com.company.repathapp.viewmodel.PotholeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MapsFragment : Fragment() {

    private lateinit var potholeViewModel: PotholeViewModel

    private val callback = OnMapReadyCallback { googleMap ->

        googleMap.setOnMapLongClickListener { location ->
            onHTTPRequest(location)

            }
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun onHTTPRequest(location: LatLng){
        val roadLat = location.latitude.toString()
        val roadLong = location.longitude.toString()
        var full = roadLat.plus(",").plus(roadLong)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://roads.googleapis.com/v1/nearestRoads?points=$full&key=AIzaSyD9y9En0zDS3fxSll0-CL8hFBzhH9lNLqg")
            .method("GET", null)
            .build()
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
               val strResponse = response.body().string().toString()
                potholeViewModel.jsonResponse.postValue(strResponse)
                Log.w("RESPONSE", strResponse)
            } else {
                Log.i("NoSuccess", "UnSuccessful${potholeViewModel.jsonResponse.value}")
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel
*/
        potholeViewModel = ViewModelProvider(requireActivity() as MapsActivity)[PotholeViewModel::class.java]

        Log.d("asd", potholeViewModel.hashCode().toString())

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


        Log.i("CREATED!!!!", "CREATEDD")
    }

}