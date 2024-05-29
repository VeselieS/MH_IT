package com.example.botttom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.botttom.databinding.ActivityCalendarMenuBinding

class calendar_menu : AppCompatActivity() {

    lateinit var binding: ActivityCalendarMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalendarMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iBackSeven.setOnClickListener {
            finish()
        }

        binding.bEntZadach.setOnClickListener {
            val intent = Intent(this, enter_zadachi::class.java)
            startActivity(intent)
        }

        binding.bEntNote.setOnClickListener {
            val intent = Intent(this, enter_note::class.java)
            startActivity(intent)
        }
    }
}