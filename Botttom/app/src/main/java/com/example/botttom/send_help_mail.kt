package com.example.botttom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import com.example.botttom.databinding.ActivitySendHelpMailBinding
import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Paint
import android.widget.TextView
import android.widget.Toast

class send_help_mail : AppCompatActivity() {

    lateinit var binding: ActivitySendHelpMailBinding
    private var myClipboard: ClipboardManager? = null
    private lateinit var myClip: ClipData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendHelpMailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tMailReport.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?;

        binding.tMailReport.setOnClickListener {
            myClip = ClipData.newPlainText("text", "help@mhandit.app")
            myClipboard?.setPrimaryClip(myClip)

            Toast.makeText(this, "Почта скопирована в буфер!", Toast.LENGTH_SHORT).show();
        }


        binding.iBackFour.setOnClickListener {
            finish()
        }
    }
}