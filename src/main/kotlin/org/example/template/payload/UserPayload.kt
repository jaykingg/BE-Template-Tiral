package org.example.template.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserPayload (
    @field: NotBlank
    val name: String,

    @field: Email
    val email: String
)
