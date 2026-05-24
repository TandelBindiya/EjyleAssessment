package com.bindiya.ejyleassessment.core

import kotlinx.serialization.Serializable

@Serializable
data class CustomerDetails(
    val id:Int?=null,
    val name: String,
    val email: String,
    val phone: String,
    val city: String
)