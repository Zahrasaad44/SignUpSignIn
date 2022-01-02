package com.example.signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.signupsignin.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    private val databaseHandler by lazy { DatabaseHandler(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.logInBtn.setOnClickListener { getUser() }
    }

    private fun getUser() {
        val emailInput = binding.signInEmailET.text.toString()
        val passwordInput = binding.signInPasswordET.text.toString()
        val result = databaseHandler.readUsers(emailInput, passwordInput)
        if (result.isNotEmpty()) {
            //Toast.makeText(this, result, Toast.LENGTH_LONG).show()

            val detailsIntent = Intent(this, Details::class.java)
            detailsIntent.putExtra("userName", result[2])
            detailsIntent.putExtra("userEmail", result[0])
            detailsIntent.putExtra("userPassword", result[1])
            detailsIntent.putExtra("userPhoneNo", result[3])

            startActivity(detailsIntent)
        } else {
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_LONG).show()
        }
    }
}