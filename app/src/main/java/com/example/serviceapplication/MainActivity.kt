package com.example.serviceapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.serviceapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            android.Manifest.permission.FOREGROUND_SERVICE,
                            android.Manifest.permission.POST_NOTIFICATIONS,
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.FOREGROUND_SERVICE_SPECIAL_USE
                        ),
                        0
                    )
                }
            }
        }*/

    }

    fun startService(view: View) {
        val intent = Intent(this, MyService::class.java)
        intent.action = "START"
        startService(intent)
    }
    fun stopService(view: View) {
        intent.action = "STOP"
        startService(Intent(this, MyService::class.java).setAction("STOP"))
    }
}