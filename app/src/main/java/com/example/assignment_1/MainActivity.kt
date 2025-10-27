package com.example.assignment_1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declare binding variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate layout with ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle system bar insets (status/navigation bar padding)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔹 Navigate to other activities
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnSignup2.setOnClickListener {
            startActivity(Intent(this, Signup2Activity::class.java))
        }
        binding.btnLogin2.setOnClickListener {
            startActivity(Intent(this, Login2Activity::class.java))
        }

        // 🔹 Open Play Store updates page
        binding.btnUpdates.setOnClickListener {
            val uri = Uri.parse("https://play.google.com/store/apps/updates")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}
