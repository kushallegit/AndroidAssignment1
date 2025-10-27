package com.example.assignment_1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class Signup2Activity : AppCompatActivity() {

    private fun isStrongPassword(p: String): Boolean {
        if (p.length !in 8..15) return false
        val hasCapital = p.any { it.isUpperCase() }
        val hasSpecial = p.any { !it.isLetterOrDigit() }
        return hasCapital && hasSpecial
    }

    // letters + numbers only, 3–30 chars
    private fun isValidUsername(u: String): Boolean {
        return u.length in 3..30 && u.all { it.isLetterOrDigit() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup2)

        // 🔹 initialize Firebase
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()

        val etUser = findViewById<EditText>(R.id.etUser)
        val etPass = findViewById<EditText>(R.id.etPass)
        val etConfirm = findViewById<EditText>(R.id.etConfirm)
        val btnSignup = findViewById<Button>(R.id.btnDoSignup)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnSignup.setOnClickListener {
            val username = etUser.text.toString().trim()
            val pass = etPass.text.toString()
            val conf = etConfirm.text.toString()

            // username validation (letters + numbers only)
            if (!isValidUsername(username)) {
                Toast.makeText(
                    this,
                    "Username must be 3–30 chars, letters & numbers only",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // password match check
            if (pass != conf) {
                Toast.makeText(this, "Passwords don’t match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // strong password check
            if (!isStrongPassword(pass)) {
                Toast.makeText(
                    this,
                    "Password must be 8–15 chars, include a capital & special character",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            // 🔹 save to Firestore
            val data = hashMapOf(
                "username" to username,
                "password" to pass    // ⚠️ demo only
            )

            db.collection("users_online").document(username).set(data)
                .addOnSuccessListener {
                    Toast.makeText(this, "Saved online (Firestore)", Toast.LENGTH_SHORT).show()
                    finish()   // go back to previous page
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        btnBack.setOnClickListener { finish() }
    }
}
