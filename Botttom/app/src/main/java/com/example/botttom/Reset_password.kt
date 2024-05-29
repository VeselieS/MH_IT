package com.example.botttom


import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.botttom.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class Reset_password : AppCompatActivity() {
    lateinit var binding: ActivityResetPasswordBinding
    public lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.bSendMail.setOnClickListener {
            if (!isFieldEmpty()){
                forgotPassword()
            }
        }
        binding.firNam4.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

    }

    private fun forgotPassword() {
        auth.sendPasswordResetEmail(binding.mailSend.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Сообщение для сброса пароля отправлено на почту!", Toast.LENGTH_LONG).show()
                    binding.mailSend.text.clear()
                    finish()
                }
                else{
                    val errorCode = (task.exception as FirebaseAuthException).errorCode
                    if (errorCode == "ERROR_USER_NOT_FOUND"){
                        binding.mailSend.error = "Такого пользователя нет!"
                    }else if(errorCode == "ERROR_INVALID_EMAIL"){
                        binding.mailSend.error = "Неверно введена почта!"
                    }else{
                        Toast.makeText(this, "Возникли проблемы при сбросе пароля!", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    private fun isFieldEmpty(): Boolean{
        binding.apply {
            if (mailSend.text.isNullOrEmpty()) mailSend.error = "Пожалуйста, введите почту, которую вы указывали при регистрации!"
            return mailSend.text.isNullOrEmpty()
        }
    }

}