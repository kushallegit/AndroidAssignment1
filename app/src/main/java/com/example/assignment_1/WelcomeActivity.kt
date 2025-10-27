package com.example.assignment_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_1.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate layout via ViewBinding
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Get username or email from Intent
        val who = intent.getStringExtra("username")
            ?: intent.getStringExtra("email")
            ?: "User"

        // 🔹 Display welcome message
        binding.tvWelcome.text = "Welcome, $who!"

        // 🔹 Back to MainActivity
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            finish()
        }
    }
}
