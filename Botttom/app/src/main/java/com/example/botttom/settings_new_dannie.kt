package com.example.botttom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.botttom.databinding.ActivitySettingsNewDannieBinding

class settings_new_dannie : AppCompatActivity() {
    lateinit var binding: ActivitySettingsNewDannieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsNewDannieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iBackTwo.setOnClickListener{
            finish()
        }

    }

}