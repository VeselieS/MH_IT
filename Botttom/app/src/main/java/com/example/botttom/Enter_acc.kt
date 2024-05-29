package com.example.botttom

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.botttom.constance.UsersModel
import com.example.botttom.databinding.ActivityEnterAccBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.*

class Enter_acc : AppCompatActivity() {
    lateinit var binding: ActivityEnterAccBinding

    private lateinit var usersList: ArrayList<UsersModel>
    private lateinit var dbRef: DatabaseReference

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityEnterAccBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.firNam3.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.tEntReg.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.tEntPass.paintFlags = binding.tEntPass.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tEntPass.setTextColor(Color.WHITE)


/*
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.setHasFixedSize(true)

        usersList = arrayListOf<UsersModel>()
        getUsersData()
*/
        binding.bEnter2.setOnClickListener {
            val userMail = binding.emailEnt.text.toString()
            val userPass = binding.passEnt.text.toString()

            if (!isFieldEmpty()){
                firebaseAuth.signInWithEmailAndPassword(userMail, userPass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (firebaseAuth.currentUser!!.isEmailVerified){
                            binding.emailEnt.text.clear()
                            binding.passEnt.text.clear()
                            Toast.makeText(this, "Вы успешно вошли в аккаунт!", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)}
                        else{
                            binding.emailEnt.error = "Подтвердите вашу почту!"
                        }
                    } else {
                        val errorCode = (it.exception as FirebaseAuthException).errorCode
                        if (errorCode == "ERROR_USER_NOT_FOUND"){
                            binding.emailEnt.error = "Такого пользователя нет!"
                        }else if(errorCode == "ERROR_WRONG_PASSWORD"){
                            binding.passEnt.error = "Неверный пароль!"
                        }else if(errorCode == "ERROR_INVALID_EMAIL"){
                            binding.emailEnt.error = "Данная почта не действительная, введите другую!"
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }
        }


        binding.tEntReg.setOnClickListener {
            finish()
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
        binding.tEntPass.setOnClickListener {
            finish()
            val intent = Intent(this, Reset_password::class.java)
            startActivity(intent)
        }
    }

    private fun isFieldEmpty(): Boolean{
        binding.apply {
            if (emailEnt.text.isNullOrEmpty()) emailEnt.error = "Пожалуйста, введите почту, которую вы указывали при регистрации!"
            if (passEnt.text.isNullOrEmpty()) passEnt.error = "Пожалуйста, введите пароль, который вы указывали при регистрации!"

            return emailEnt.text.isNullOrEmpty() || passEnt.text.isNullOrEmpty()
        }
    }



/*
    private fun getUsersData(){
        binding.rvUsers.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                usersList.clear()
                if (snapshot.exists()){
                    for (userSnap in snapshot.children){
                        val userData = userSnap.getValue(UsersModel::class.java)
                        usersList.add(userData!!)
                    }
                    val mAdapter = UserAdapter(usersList)
                    binding.rvUsers.adapter = mAdapter

                    binding.rvUsers.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
*/

/*
    fun onClickGoReg(view : View){
        finish()
        val intent = Intent(this, Registration::class.java)
        startActivity(intent)
    }

    fun onClickGoEnt(view : View){
        if (!isFieldEmpty()) {/*
            val intentEnt = Intent()
            intentEnt.putExtra(Constance.MAIL, bindingClass.emailEnt.text.toString())
            intentEnt.putExtra(Constance.PASS, bindingClass.passEnt.text.toString())
            setResult(RESULT_OK, intentEnt)
            finish()*/
            val intent = Intent(this, forscreen::class.java)
            startActivity(intent)
        }
    }

    private fun isFieldEmpty(): Boolean{
        binding.apply {
            if (emailEnt.text.isNullOrEmpty()) emailEnt.error = "Заполните поле!"
            if (passEnt.text.isNullOrEmpty()) passEnt.error = "Заполните поле!"

            return emailEnt.text.isNullOrEmpty() || passEnt.text.isNullOrEmpty()
        }
    }*/
}