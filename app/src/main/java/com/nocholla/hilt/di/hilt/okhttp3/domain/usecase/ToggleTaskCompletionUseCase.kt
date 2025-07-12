package com.nocholla.hilt.di.hilt.okhttp3.domain.usecase

import com.nocholla.hilt.di.hilt.okhttp3.domain.model.Task
import com.nocholla.hilt.di.hilt.okhttp3.domain.repository.TaskRepository
import javax.inject.Inject

class ToggleTaskCompletionUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task): Result<Task> {
        return try {
            val updatedTask = task.copy(isCompleted = !task.isCompleted)
            val result = repository.toggleTaskCompletion(updatedTask)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}