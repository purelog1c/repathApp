package com.company.repathapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.company.repathapp.R
import com.company.repathapp.viewmodel.MapAttributesViewModel
import com.google.android.gms.maps.GoogleMap
import android.app.Activity
import com.google.android.gms.maps.SupportMapFragment


class MapAttributesFragment : Fragment() {

    companion object {
        fun newInstance() = MapAttributesFragment()
    }


    private lateinit var mapAttributesViewModel: MapAttributesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_attributes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mapAttributesViewModel = ViewModelProvider(this)[MapAttributesViewModel::class.java]






        // TODO: Use the ViewModel
    }

}