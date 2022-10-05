package com.example.myapplicationtask.ui.main

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplicationtask.R
import com.example.myapplicationtask.utils.getNavOptions

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val splashTime: Long = 1000 // 1 sec

    override fun onStart() {
        super.onStart()
        goToWelcome()
    }

    private fun goToWelcome() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().popBackStack()
            findNavController().navigate(R.id.registerFragment, null, getNavOptions)
        }, splashTime)
    }

}