package com.company.repathapp.view

import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProvider

import com.company.repathapp.databinding.MapAttributesFragmentBinding
import com.company.repathapp.viewmodel.PotholeViewModel
import com.google.android.gms.maps.GoogleMap

import com.google.android.gms.maps.model.LatLng

class PotholeFragment : Fragment(), View.OnTouchListener {

    private lateinit var binding: MapAttributesFragmentBinding
    companion object {
        fun newInstance() = PotholeFragment()
    }

   private lateinit var potholeViewModelProvider: PotholeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MapAttributesFragmentBinding.inflate(this.layoutInflater)
        binding.lifecycleOwner = this
        potholeViewModelProvider = ViewModelProvider(this)[PotholeViewModel::class.java]
        binding.potholeViewModel = potholeViewModelProvider

        binding.HighRisk.setOnTouchListener(this)
        binding.MediumRisk.setOnTouchListener(this)
        binding.LowRisk.setOnTouchListener(this)

        potholeViewModelProvider.getPotholeModel()!!.observe(viewLifecycleOwner,{Pothole ->
            val tem = Pothole.getLocation()
/*
            Pothole.getUserID()
            Pothole.setLocation(location)*/
            Log.i("YES",tem.toString())
        })
        return binding.root
    }

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


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        val button:Button = (v as Button)
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                button.id = (v as Button).id

               if(button == binding.HighRisk){
                   binding.MediumRisk.isEnabled = false
                   binding.LowRisk.isEnabled = false
               }
                if(button == binding.MediumRisk){
                    binding.HighRisk.isEnabled = false
                    binding.LowRisk.isEnabled = false
                }
                if(button == binding.LowRisk){
                    binding.MediumRisk.isEnabled = false
                    binding.HighRisk.isEnabled = false
                }
            }
            MotionEvent.ACTION_UP ->{

                binding.LowRisk.isEnabled = true
                binding.MediumRisk.isEnabled = true
                binding.HighRisk.isEnabled = true
                return true
            }
        }
        Log.i("LOGGER" , button.toString())
        return false
    }
}

