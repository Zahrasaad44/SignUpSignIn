package com.example.signupsignin

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.signupsignin.databinding.ActivityDetailsBinding

class Details : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        showUserInfo()

        binding.SignOutBtn.setOnClickListener { showLogOutDialog() }
    }


    @SuppressLint("SetTextI18n")
    private fun showUserInfo() {
        val userN = intent.getStringExtra("userName")
        val userE = intent.getStringExtra("userEmail")
        val userP = intent.getStringExtra("userPassword")
        val userPh = intent.getStringExtra("userPhoneNo")

        binding.welcomeTV.text = "Hello $userN!"
        binding.detailsTV.text = "Your Credentials are:\n\n Phone Number: $userPh\n\nEmail: $userE\n\nPassword: $userP"
    }


    private fun showLogOutDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Sign out Confirmation")
        dialogBuilder.setMessage("Are you sure you want to sign out?")

            .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ -> goToMain() })

            .setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, _ -> dialog.cancel() })
        dialogBuilder.show()

//        val alert = dialogBuilder.create()
//        alert.show()

    }

    private fun goToMain() {
        val mainIntent = Intent(this, MainActivity::class.java)
        // To go to the MainActivity WITHOUT allowing the user to click "back" to go to Details activity(NOTE: this line isn't my solution)
        mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(mainIntent)
    }

}