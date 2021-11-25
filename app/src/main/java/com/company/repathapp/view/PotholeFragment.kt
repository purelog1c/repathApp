package com.company.repathapp.view


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.Repo.PotholeLocation
import com.company.repathapp.databinding.FragmentPotholeBinding

import com.company.repathapp.viewmodel.PotholeViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.gson.Gson

class PotholeFragment : Fragment() {

    private lateinit var potholeViewModel: PotholeViewModel
    private lateinit var binding: FragmentPotholeBinding


    private lateinit var slideUp: Animation
    private lateinit var slideDown: Animation
    private var riskID: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPotholeBinding.inflate(this.layoutInflater)
        binding.lifecycleOwner = this
        potholeViewModel =
            ViewModelProvider(requireActivity() as MapsActivity)[PotholeViewModel::class.java]
        binding.potholeViewModel = potholeViewModel
        binding.potholeFrameLayout.visibility = View.GONE

        /*
         binding.cancelAction.setOnClickListener{
             downAnimation()
         }
         binding.potholeLayout.visibility = View.GONE*/


// SET MAPS MARKER PROPERTIES DEPENDING ON SELECTED RADIO BUTTON
        /*potholeViewModelProvider.getPotholeTypeByID()?.observe(viewLifecycleOwner, { ID ->
            when(ID){
                binding.smallPothole.id ->{ setRiskID(binding.smallPothole.id)
                    setMarkerOptions(MarkerOptions().icon(context?.let { bitmapDescriptorFromVector(it.applicationContext,R.drawable.ic_pothole_low) }))
                    setMarkerOptions(MarkerOptions().position(potholeViewModelProvider.potholePosition))
                }
                binding.avaragePothole.id ->{ setRiskID(binding.avaragePothole.id)
                    setMarkerOptions(MarkerOptions().icon(context?.let { bitmapDescriptorFromVector(it.applicationContext,R.drawable.ic_pothole_medium) }))
                    setMarkerOptions(MarkerOptions().position(potholeViewModelProvider.potholePosition))
                }
                binding.deepPothole.id ->{ setRiskID(binding.deepPothole.id)
                    setMarkerOptions(MarkerOptions().icon(context?.let { bitmapDescriptorFromVector(it.applicationContext,R.drawable.ic_pothole_high) }))
                    setMarkerOptions(MarkerOptions().position(potholeViewModelProvider.potholePosition))
                }
            }
        })*/

        potholeViewModel._jsonResponse.observe(viewLifecycleOwner) { jsonResponse ->
            if (jsonResponse.length > 5) {
               var gson = Gson()
                val potholeLocation = gson.fromJson(jsonResponse, PotholeLocation::class.java)
                upAnimation()
                Log.w("CATCH HERE", potholeLocation.snappedPoints[0].location.toString())


            } else {
                //Log.w("ELSE CATCH HERE", "ELSE CATCH HERE")
            }
        }
        return binding.root
    }

    private fun setRiskID(riskID: Int) {
        this.riskID = riskID
    }

    fun getRiskID(): Int? {
        return riskID
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.w("IAM ALIVE", "IA M ALIVE")


    }

/*    private fun setMarkerOptions(markerOptions: MarkerOptions){
        marker = markerOptions
    }

    fun getMarkerOptions():MarkerOptions{
        return marker
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        riskID = null
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    fun upAnimation() {
        binding.potholeFrameLayout.visibility = View.VISIBLE
        binding.potholeFrameLayout.startAnimation(slideUp)
    }
    fun downAnimation() {
        binding.potholeFrameLayout.startAnimation(slideDown)
        binding.potholeFrameLayout.visibility = View.GONE
    }

}
