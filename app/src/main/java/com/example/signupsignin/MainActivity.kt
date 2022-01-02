package com.example.signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signupsignin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.signInBtn.setOnClickListener { goToSignIn() }

        binding.signUpBtn.setOnClickListener { goToSignUp() }
    }

   private fun goToSignIn() {
        val signInIntent = Intent(this, SignIn::class.java)
        startActivity(signInIntent)
    }

   private fun goToSignUp() {
        val signUpIntent = Intent(this, SignUp::class.java)
        startActivity(signUpIntent)
    }
}