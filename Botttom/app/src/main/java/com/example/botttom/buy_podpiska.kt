package com.example.botttom

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.botttom.databinding.ActivityBuyPodpiskaBinding

class buy_podpiska : AppCompatActivity() {
    lateinit var binding:ActivityBuyPodpiskaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyPodpiskaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.firNam2.paintFlags = binding.firNam2.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.firNam2.setTextColor(Color.WHITE)
        binding.iBackThree.setOnClickListener {
            finish()
        }
    }

}