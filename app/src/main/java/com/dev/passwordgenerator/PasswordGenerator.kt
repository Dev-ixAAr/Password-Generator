package com.dev.passwordgenerator

import java.util.*

object PasswordGenerator {

    private val upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz"
    private val digits = "0123456789"
    private val specialChars = "!@#$%^&*()-_+=<>?/"

    // Function to generate a password based on the selected criteria
    fun generatePassword(length: Int, includeUppercase: Boolean, includeNumbers: Boolean, includeSpecial: Boolean): String {
        val allowedChars = StringBuilder(lowerCaseLetters)

        if (includeUppercase) allowedChars.append(upperCaseLetters)
        if (includeNumbers) allowedChars.append(digits)
        if (includeSpecial) allowedChars.append(specialChars)

        val password = StringBuilder()
        val random = Random()

        // Generate a password with random characters from allowedChars
        for (i in 0 until length) {
            val randomIndex = random.nextInt(allowedChars.length)
            password.append(allowedChars[randomIndex])
        }

        return password.toString()
    }
}
