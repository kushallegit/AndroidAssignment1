package com.example.assignment_1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_1.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    // Declare ViewBinding variable
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate layout with ViewBinding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Signup button logic
        binding.btnDoSignup.setOnClickListener {
            val username = binding.etUser.text.toString().trim()
            val password = binding.etPass.text.toString()
            val confirm = binding.etConfirm.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirm) {
                Toast.makeText(this, "Passwords don’t match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save credentials locally using SharedPreferences
            val sharedPref = getSharedPreferences("local_auth", Context.MODE_PRIVATE)
            sharedPref.edit()
                .putString("username", username)
                .putString("password", password)
                .apply()

            Toast.makeText(this, "Signup successful! Saved locally.", Toast.LENGTH_SHORT).show()
            finish() // Go back to Main Page
        }

        // 🔹 Back button logic
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
