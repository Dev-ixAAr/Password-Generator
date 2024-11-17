package com.dev.passwordgenerator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Remove the ActionBar
        supportActionBar?.hide()

        val githubLink: Button = findViewById(R.id.githubLink)
        githubLink.setOnClickListener {
            // Open the GitHub profile URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Dev-ixAAr"))
            startActivity(intent)
        }

        // Contact Developer Button
        val contactButton: Button = findViewById(R.id.contactDeveloperButton)
        contactButton.setOnClickListener {
            // Open email app to contact the developer
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:isharaweerasuri@gmail.com") // Only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, arrayOf("developer@example.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Feedback/Support for Password Generator")
            }
            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(emailIntent)
            }
        }
    }
}
