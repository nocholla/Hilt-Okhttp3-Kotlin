package com.nocholla.hilt.di.hilt.okhttp3.domain.model

// This is the pure domain model, focused on business logic
data class Task(
    val id: Int,
    val name: String,
    val isCompleted: Boolean
)
