package com.company.repathapp.view

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.company.repathapp.R
import com.company.repathapp.viewmodel.MapAttributesViewModel
import androidx.fragment.app.FragmentManager
import com.company.repathapp.databinding.MapAttributesFragmentBinding

import com.google.android.material.floatingactionbutton.FloatingActionButton





class MapAttributesFragment : Fragment() {
    private val viewModel: MapAttributesViewModel by viewModels()
    companion object {
        fun newInstance() = MapAttributesFragment()
    }

   private lateinit var mapAttributesViewModelProvider: MapAttributesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_attributes_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapAttributesViewModelProvider = ViewModelProvider(this)[MapAttributesViewModel::class.java]

        val binding = MapAttributesFragmentBinding.inflate(this.layoutInflater)
        binding.lifecycleOwner = this
        binding.mapAttributesViewModel = mapAttributesViewModelProvider



    }

    fun upAnimation(){

    }
}