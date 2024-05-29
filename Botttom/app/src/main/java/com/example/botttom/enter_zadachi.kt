package com.example.botttom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.botttom.databinding.ActivityEnterZadachiBinding

class enter_zadachi : AppCompatActivity() {

    lateinit var binding:ActivityEnterZadachiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterZadachiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iBackEight.setOnClickListener {
            finish()
        }


    }
}