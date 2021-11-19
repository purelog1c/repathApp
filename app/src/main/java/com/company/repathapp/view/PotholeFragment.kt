package com.company.repathapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.databinding.MapAttributesFragmentBinding
import com.company.repathapp.viewmodel.PotholeViewModel
import com.google.android.gms.maps.model.LatLng

import com.company.repathapp.R
import kotlin.math.absoluteValue


class PotholeFragment : Fragment() {

    private lateinit var binding: MapAttributesFragmentBinding
    private lateinit var potholeViewModelProvider: PotholeViewModel
    private lateinit var slideUp: Animation
    private lateinit var slideDown: Animation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        potholeViewModelProvider = ViewModelProvider(this)[PotholeViewModel::class.java]
        binding = MapAttributesFragmentBinding.inflate(this.layoutInflater)
        binding.lifecycleOwner = this
        binding.potholeViewModel = potholeViewModelProvider
        binding.cancelAction.setOnClickListener{
            downAnimation()
        }
        binding.potholeLayout.visibility = View.GONE
        potholeViewModelProvider.getPotholeModel()!!.observe(viewLifecycleOwner, { Pothole ->

            val tem = Pothole.getLocation()
/*
            Pothole.getUserID()
            Pothole.setLocation(location)*/
            Log.i("YES", tem.toString())
        })

        potholeViewModelProvider.getPotholeTypeByID()?.observe(viewLifecycleOwner, { ID ->
            when(ID){
                binding.smallPothole.id ->{
                }
            }
        })


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    fun setCoordinates(mLatitude: Double, mLongitude: Double) {
        val location = LatLng(mLatitude, mLongitude)
        potholeViewModelProvider.location = location
        potholeViewModelProvider.CreatePothole()
    }


    fun upAnimation() {
        binding.potholeLayout.visibility = View.VISIBLE
        binding.potholeLayout.startAnimation(slideUp)
    }
    fun downAnimation() {
        binding.potholeLayout.startAnimation(slideDown)
        binding.potholeLayout.visibility = View.GONE
    }


}
