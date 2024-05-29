package com.example.botttom

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.botttom.constance.UsersModel
import com.example.botttom.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.util.regex.Pattern

class Registration : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val FirNamView = findViewById<TextView>(R.id.firNam2)
        FirNamView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        binding.tRegEnt.paintFlags = binding.tRegEnt.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tRegEnt.setTextColor(Color.WHITE)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.bZareg.setOnClickListener {
            val userName = binding.NameReg.text.toString()
            val userMail = binding.EmailReg.text.toString()
            val userPass = binding.PassReg.text.toString()
            val userSecPass = binding.SecPassReg.text.toString()

            val authErrors = mapOf("ERROR_INVALID_CUSTOM_TOKEN" to R.string.error_login_custom_token,
                "ERROR_CUSTOM_TOKEN_MISMATCH" to R.string.error_login_custom_token_mismatch,
                "ERROR_INVALID_CREDENTIAL" to R.string.error_login_credential_malformed_or_expired,
                "ERROR_INVALID_EMAIL" to R.string.error_login_invalid_email,
                "ERROR_WRONG_PASSWORD" to R.string.error_login_wrong_password,
                "ERROR_USER_MISMATCH" to R.string.error_login_user_mismatch,
                "ERROR_REQUIRES_RECENT_LOGIN" to R.string.error_login_requires_recent_login,
                "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" to R.string.error_login_accounts_exits_with_different_credential,
                "ERROR_EMAIL_ALREADY_IN_USE" to  R.string.error_login_email_already_in_use,
                "ERROR_CREDENTIAL_ALREADY_IN_USE" to R.string.error_login_credential_already_in_use,
                "ERROR_USER_DISABLED" to R.string.error_login_user_disabled,
                "ERROR_USER_TOKEN_EXPIRED" to R.string.error_login_user_token_expired,
                "ERROR_USER_NOT_FOUND" to R.string.error_login_user_not_found,
                "ERROR_INVALID_USER_TOKEN" to R.string.error_login_invalid_user_token,
                "ERROR_OPERATION_NOT_ALLOWED" to R.string.error_login_operation_not_allowed,
                "ERROR_WEAK_PASSWORD" to R.string.error_login_password_is_weak)


            if (!isFieldEmpty()){
                if (isValidPassword()) {
                    if (isDoublePassword()){
                        firebaseAuth.createUserWithEmailAndPassword(userMail, userPass).addOnCompleteListener {
                            if (it.isSuccessful){
                                saveUsersData()
                                sendVerifyMail()
                                binding.NameReg.text.clear()
                                binding.EmailReg.text.clear()
                                binding.PassReg.text.clear()
                                binding.SecPassReg.text.clear()
                                firebaseAuth.signOut()
                                Toast.makeText(this, "Регистрация прошла успешно. Подтвердите свою почту!", Toast.LENGTH_LONG).show()
                                finish()
                            }else {
                                val errorCode = (it.exception as FirebaseAuthException).errorCode
                                if (errorCode == "ERROR_INVALID_EMAIL"){
                                    binding.EmailReg.error = "Неверно введена почта!"
                                }else if(errorCode == "ERROR_EMAIL_ALREADY_IN_USE"){
                                    binding.EmailReg.error = "Пользователь с такой почтой уже существует!"
                                }else{
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                }
            }


        }

        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        /*
        binding.bZareg.setOnClickListener {
            if (!isFieldEmpty()){
                if (isValidPassword()) {
                    if (isDoublePassword()){
                        saveUsersData()
                        finish()
                    }
                }
            }
        }*/
        binding.tRegEnt.setOnClickListener {
            finish()
            val intent = Intent(this, Enter_acc::class.java)
            startActivity(intent)
        }
    }

    private fun saveUsersData(){
        val userName = binding.NameReg.text.toString()
        val userMail = binding.EmailReg.text.toString()
        val userId = dbRef.push().key!!
        val users = UsersModel(userId, userName, userMail)
        dbRef.child(userId).setValue(users)
    }

    private fun isFieldEmpty(): Boolean{
        binding.apply {
            if (NameReg.text.isNullOrEmpty()) NameReg.error = "Пожалуйста, введите имя!"
            if (EmailReg.text.isNullOrEmpty()) EmailReg.error = "Пожалуйста, введите почту!"
            if (PassReg.text.isNullOrEmpty()) PassReg.error = "Пожалуйста, введите пароль!"
            if (SecPassReg.text.isNullOrEmpty()) SecPassReg.error = "Пожалуйста, введите повторно пароль!"

            return NameReg.text.isNullOrEmpty() || EmailReg.text.isNullOrEmpty() ||
                    PassReg.text.isNullOrEmpty() || SecPassReg.text.isNullOrEmpty()
        }
    }

    internal fun isValidPassword(): Boolean {
        binding.apply {
            if (PassReg.text.toString().length < 8) {PassReg.error = "Пароль должен состоять из 8 символов!"
                return false}
            if (PassReg.text.toString().filter { it.isDigit() }.firstOrNull() == null) {PassReg.error = "Пароль должен содержать хотя бы одну цифру!"
                return false}
            if (PassReg.text.toString().filter { it.isLetter() }.filter { it.isUpperCase() }
                    .firstOrNull() == null) {PassReg.error = "Пароль должен содержать хотя бы одну заглавную букву!"
                return false}
            if (PassReg.text.toString().filter { it.isLetter() }.filter { it.isLowerCase() }
                    .firstOrNull() == null) {PassReg.error = "Пароль должен содержать хотя бы одну строчную букву!"
                return false}

            return true
        }
    }
    internal fun isDoublePassword(): Boolean {
        binding.apply {
            if (PassReg.text.toString() != SecPassReg.text.toString()) {SecPassReg.error = "Пароль должен повторять первый!"
                return false}
            return true
        }
    }
    private fun sendVerifyMail(){
        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Регистрация прошла успешно. Подтвердите свою почту!", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(this, "Ошибка при подтверждении почты!", Toast.LENGTH_LONG).show()
            }
        }
    }


}