package com.example.botttom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.botttom.databinding.ActivityPolitConfBinding

class polit_conf : AppCompatActivity() {
    lateinit var binding:ActivityPolitConfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPolitConfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iBackSix.setOnClickListener {
            finish()
        }
    }
}