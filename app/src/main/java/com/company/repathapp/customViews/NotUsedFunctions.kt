package com.company.repathapp.customViews

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button


// ON TOUCH FUNCTIONALITY

/*
private fun Button.onTouch(touch: (view: View, motionEvent: MotionEvent) -> Unit) {
    setOnTouchListener { v, event ->
        touch(v,event)
        v.performClick()
        true
    }
}
private fun onTouchButton(v: View, event: MotionEvent) {
    val button: Button = (v as Button)
    when(event.action){
        MotionEvent.ACTION_DOWN -> {
            button.id = (v).id

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
            Log.i("BUTTONCLICKED", button.id.toString())

        }
    }
}*/

/*        binding.HighRisk.onTouch{v,event->
            onTouchButton(v,event)
        }
        binding.MediumRisk.onTouch{v,event->
            onTouchButton(v,event)
        }
        binding.LowRisk.onTouch{v,event->
            onTouchButton(v,event)
        }*/
