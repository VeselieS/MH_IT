package com.example.botttom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.botttom.databinding.ActivityEnterNoteBinding

class enter_note : AppCompatActivity() {
    lateinit var binding: ActivityEnterNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iBackNine.setOnClickListener {
            finish()
        }
    }
}