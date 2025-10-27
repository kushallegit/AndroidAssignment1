package com.example.assignment_1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the layout using ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Login button click listener
        binding.btnLogin.setOnClickListener {
            val uInput = binding.etUser.text.toString().trim()
            val pInput = binding.etPass.text.toString()

            val sp = getSharedPreferences("local_auth", Context.MODE_PRIVATE)
            val savedUser = sp.getString("username", null)
            val savedPass = sp.getString("password", null)

            if (uInput == savedUser && pInput == savedPass) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(this, WelcomeActivity::class.java)
                        .putExtra("username", uInput)
                )
            } else {
                Toast.makeText(
                    this,
                    "Username or password is incorrect. Try again!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // 🔹 Back button click listener
        binding.btnBack.setOnClickListener {
            finish() // return to previous activity
        }
    }
}
