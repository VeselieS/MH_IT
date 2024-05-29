package com.example.botttom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.botttom.databinding.ActivityAboutProgramBinding

class about_program : AppCompatActivity() {
    lateinit var binding: ActivityAboutProgramBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iBackFive.setOnClickListener {
            finish()
        }
    }
}