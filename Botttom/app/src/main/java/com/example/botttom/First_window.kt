package com.example.botttom


import android.app.Activity
import android.content.ContentProviderClient
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.example.botttom.databinding.FirstWindowBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class First_window : AppCompatActivity() {
    private var registr: ActivityResultLauncher<Intent>? = null
    private var enterIn: ActivityResultLauncher<Intent>? = null
    lateinit var binding: FirstWindowBinding
    private var name: String = "empty"
    private var mail: String = "empty"
    private var pass: String = "empty"
    private var secPass: String = "empty"
    var user = User()

    lateinit var launcher: ActivityResultLauncher<Intent>

    private lateinit var btnEnter: Button
    private lateinit var btnReg: Button

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = FirstWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        binding.bEnter.setOnClickListener {
            val intent = Intent(this, Enter_acc::class.java)
            startActivity(intent)
        }
        binding.bReg.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account != null){
                    firebaseAuthWithGoogle(account.idToken!!)
                }

            }catch (e: ApiException){
                Log.d("TAG", e.toString())
            }
        }
        binding.bGoogle.setOnClickListener {
            signInWithGoogle()
        }
        /*registr = registerForActivityResult(StartActivityForResult()){
            result: ActivityResult ->
            if (result.resultCode == RESULT_OK){
                val text = result.data?.getStringExtra(Constance.NAME) + result.data?.getStringExtra(Constance.MAIL) +
                 result.data?.getStringExtra(Constance.PASS) + result.data?.getStringExtra(Constance.SECPASS)
                Log.d("MyLog", text.toString())

                name = result.data?.getStringExtra(Constance.NAME)!!
                mail = result.data?.getStringExtra(Constance.MAIL)!!
                pass = result.data?.getStringExtra(Constance.PASS)!!
                secPass = result.data?.getStringExtra(Constance.SECPASS)!!

                val textInfo = "$name, вы успешно прошли регистрацию. Теперь войдите в аккаунт!"
                bindingClass.tEntInf.text = textInfo
                Log.d("MyLog", textInfo)
            }
        }

        enterIn = registerForActivityResult(StartActivityForResult()){
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK){
                val text = result.data?.getStringExtra(Constance.MAIL) + result.data?.getStringExtra(Constance.PASS)
                Log.d("MyLog", text.toString())

                val m = result.data?.getStringExtra(Constance.MAIL)
                val p = result.data?.getStringExtra(Constance.PASS)
                if (mail == m && pass == p){
                    val textInfo = "$mail $pass"
                    bindingClass.tEntInf.text = textInfo
                    Log.d("MyLog", textInfo)
                } else {
                    bindingClass.tEntInf.text = "Нет такого аккаунта"
                    Log.d("MyLog", "Нет такого аккаунта")
                }
            }
        }

*/
        val FirNamView = findViewById<TextView>(R.id.FirNam)
        FirNamView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }


    private fun getClient():GoogleSignInClient{
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle(){
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
                Log.d("TAG", "Good connect")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Log.d("TAG", "Bad connect")
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (firebaseAuth.currentUser!=null){
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }



/*
    fun onClickGoEnt(view : View){
        enterIn?.launch(Intent(this, Enter_acc::class.java))
    }

    fun onClickGoReg(view : View){
        registr?.launch(Intent(this, Registration::class.java))
    }

*/

}