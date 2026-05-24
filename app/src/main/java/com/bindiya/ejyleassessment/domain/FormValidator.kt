package com.bindiya.ejyleassessment.domain


object FormValidator {

    fun executeName(name: String): String? {
        if (name.isBlank()) return "Name is required"
        if (name.length < 2) return "Minimum 2 characters required"
        if (!name.all { it.isLetter() || it.isWhitespace() }) {
            return "No numbers or special characters allowed"
        }
        return null
    }

    fun executeEmail(email: String): String? {
        if (email.isBlank()) return "Email is required"
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
        if (!email.matches(emailRegex)) return "Invalid email format"
        return null
    }

    fun executePhone(phone: String): String? {
        if (phone.isBlank()) return "Phone number is required"
        if (!phone.all { it.isDigit() }) return "Digits only"
        if (phone.length !in 7..15) return "Must be between 7 and 15 digits"
        return null
    }

    fun executeCity(city: String): String? {
        val allowedCities = listOf("Dubai", "Abu Dhabi", "Sharjah", "Riyadh")
        if (city.isBlank() || city !in allowedCities) {
            return "Please select a valid city"
        }
        return null
    }

}