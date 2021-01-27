package com.scainitiative.kotlinscainitiativeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    private lateinit var  mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val w = window;
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        mNavController = Navigation.findNavController(this, R.id.fragment)
        setDestinationListener()

    }

    private fun setDestinationListener() {
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            val dest = resources.getResourceName(destination.id)

            when(destination.id) {
                R.id.homeFragment -> hideCustomToolBar()
                R.id.addNoteFragment -> hideCustomToolBar()
                else -> showCustomToolBar()
            }
        }
    }

    private fun showCustomToolBar() {
        supportActionBar?.show()
    }

    private fun hideCustomToolBar() {
        supportActionBar?.hide()
    }
}
