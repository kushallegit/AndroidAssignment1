package com.example.assignment_1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_1.databinding.ActivitySignup2Binding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class Signup2Activity : AppCompatActivity() {

    // ViewBinding instance
    private lateinit var binding: ActivitySignup2Binding

    // Strong password validation
    private fun isStrongPassword(p: String): Boolean {
        if (p.length !in 8..15) return false
        val hasCapital = p.any { it.isUpperCase() }
        val hasSpecial = p.any { !it.isLetterOrDigit() }
        return hasCapital && hasSpecial
    }

    // Username validation (letters + numbers only, 3–30 chars)
    private fun isValidUsername(u: String): Boolean {
        return u.length in 3..30 && u.all { it.isLetterOrDigit() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate layout via ViewBinding
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()

        // 🔹 Signup button logic
        binding.btnDoSignup.setOnClickListener {
            val username = binding.etUser.text.toString().trim()
            val pass = binding.etPass.text.toString()
            val conf = binding.etConfirm.text.toString()

            // Validate username
            if (!isValidUsername(username)) {
                Toast.makeText(
                    this,
                    "Username must be 3–30 chars, letters & numbers only",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Check password match
            if (pass != conf) {
                Toast.makeText(this, "Passwords don’t match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check strong password
            if (!isStrongPassword(pass)) {
                Toast.makeText(
                    this,
                    "Password must be 8–15 chars, include a capital & special character",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            // 🔹 Save to Firestore
            val data = hashMapOf(
                "username" to username,
                "password" to pass // avoid storing plain text in real apps
            )

            db.collection("users_online").document(username).set(data)
                .addOnSuccessListener {
                    Toast.makeText(this, "Saved online (Firestore)", Toast.LENGTH_SHORT).show()
                    finish() // go back to previous page
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // 🔹 Back button logic
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
