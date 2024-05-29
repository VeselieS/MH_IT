package com.example.botttom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.botttom.databinding.ActivityRedaktSettingsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class redakt_settings : AppCompatActivity() {

    private lateinit var binding: ActivityRedaktSettingsBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedaktSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val textView = findViewById<TextView>(R.id.tEditProfile) as TextView
        textView?.setOnClickListener()
        {
            val intent = Intent(this, settings_new_dannie::class.java)
            startActivity(intent)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googlesigninclient = GoogleSignIn.getClient(this,gso)

        auth = FirebaseAuth.getInstance()
        binding.bViityIzAccaunta.setOnClickListener {
            googlesigninclient.signOut()
            auth.signOut()

            val intent = Intent(this, First_window::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        binding.iBack.setOnClickListener{
            finish()
        }
        binding.bOPrilozhenii.setOnClickListener {
            val intent = Intent(this, about_program::class.java)
            startActivity(intent)
        }
        binding.bPodderzhka.setOnClickListener {
            val intent = Intent(this, send_help_mail::class.java)
            startActivity(intent)
        }
        binding.bPolitKonfPolzSogl.setOnClickListener {
            val intent = Intent(this, polit_conf::class.java)
            startActivity(intent)
        }

    }

}