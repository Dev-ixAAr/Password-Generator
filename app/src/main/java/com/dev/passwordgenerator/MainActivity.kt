package com.dev.passwordgenerator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import java.util.*
import android.view.Menu
import android.view.MenuInflater


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Remove the ActionBar
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        // Initialize UI components
        val lengthInput: EditText = findViewById(R.id.lengthInput)
        val includeUppercase: CheckBox = findViewById(R.id.includeUppercase)
        val includeNumbers: CheckBox = findViewById(R.id.includeNumbers)
        val includeSpecial: CheckBox = findViewById(R.id.includeSpecial)
        val generateButton: Button = findViewById(R.id.generateButton)
        val passwordOutput: TextView = findViewById(R.id.passwordOutput)
        val passwordStrength: TextView = findViewById(R.id.passwordStrength)
        val copyButton: Button = findViewById(R.id.copyButton)
        val aboutButton: Button = findViewById(R.id.aboutButton)

        lengthInput.setText("8")

        // Set click listener to navigate to AboutActivity
        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        // Button click listener to generate password
        generateButton.setOnClickListener {
            val lengthText = lengthInput.text.toString()
            if (lengthText.isEmpty()) {
                Toast.makeText(this, "Please enter a valid length!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val length = lengthText.toInt()
            val uppercase = includeUppercase.isChecked
            val numbers = includeNumbers.isChecked
            val special = includeSpecial.isChecked

            // Generate password using PasswordGenerator
            val password = PasswordGenerator.generatePassword(length, uppercase, numbers, special)

            // Safe call for updating the passwordOutput text
            passwordOutput.text = password ?: ""

            // Check the strength of the generated password
            val strength = checkPasswordStrength(password)
            passwordStrength.text = "Strength: $strength"
            passwordStrength.setTextColor(getStrengthColor(strength))
        }

        // Copy the generated password to clipboard
        copyButton.setOnClickListener {
            val password = passwordOutput.text.toString()
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Generated Password", password)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Password copied to clipboard!", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to check password strength
    private fun checkPasswordStrength(password: String): String {
        return when {
            password.length >= 12 &&
                    password.any { it.isUpperCase() } &&
                    password.any { it.isDigit() } &&
                    password.any { "!@#$%^&*()-_+=<>?/".contains(it) } -> {
                "Strong"
            }
            password.length >= 8 &&
                    (password.any { it.isUpperCase() } || password.any { it.isDigit() }) -> {
                "Medium"
            }
            else -> {
                "Weak"
            }
        }
    }

    // Function to return color based on password strength
    private fun getStrengthColor(strength: String): Int {
        return when (strength) {
            "Strong" -> Color.GREEN
            "Medium" -> Color.YELLOW
            else -> Color.RED
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) // Inflate your menu resource
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                // Navigate to AboutActivity
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
