package com.example.coffeshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeshop.databinding.ActivitySplash2Binding

class SplashActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySplash2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Initialize binding first
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Use binding safely after initialization
        binding.startBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish() // optional: closes splash so user can’t go back
        }
    }
}