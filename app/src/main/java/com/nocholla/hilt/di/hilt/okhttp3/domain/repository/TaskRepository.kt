package com.nocholla.hilt.di.hilt.okhttp3.domain.repository

import com.nocholla.hilt.di.hilt.okhttp3.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getTasks(): Flow<List<Task>>
    suspend fun addTask(name: String): Task
    suspend fun toggleTaskCompletion(task: Task): Task
    suspend fun removeTask(taskId: Int)
}