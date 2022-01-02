package com.example.signupsignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.signupsignin.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private val databaseHandler by lazy { DatabaseHandler(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.regSignUpBtn.setOnClickListener { addUser() }
    }

    private fun addUser() {
        val email = binding.regEmailET.text
        val name = binding.regNameET.text
        val phoneNo = binding.regPhoneET.text
        val password = binding.regPasswordET.text

        if(email.isNotEmpty() && name.isNotEmpty() && phoneNo.isNotEmpty() && password.isNotEmpty()) {
          val added =  databaseHandler.saveUser(email.toString(), name.toString(), phoneNo.toString(), password.toString())
            if(added < 0) {
                Toast.makeText(this, "Email Already Used", Toast.LENGTH_LONG).show()
            } else {
                email.clear()
                name.clear()
                phoneNo.clear()
                password.clear()
                Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show()
        }
    }
}