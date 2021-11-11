package com.company.repathapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.viewmodel.MapAttributesViewModel


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapAttributesViewModel = ViewModelProvider(this)[MapAttributesViewModel::class.java]




    }
}