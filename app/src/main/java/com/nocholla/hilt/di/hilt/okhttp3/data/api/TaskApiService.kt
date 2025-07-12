package com.nocholla.hilt.di.hilt.okhttp3.data.api

import com.google.gson.Gson
import com.nocholla.hilt.di.hilt.okhttp3.data.model.TaskDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskApiService @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson
) {
    private val jsonMediaType = "application/json".toMediaType()

    suspend fun getTasks(): List<TaskDto> = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/todos")
            .get()
            .build()
        okHttpClient.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                gson.fromJson(response.body!!.string(), Array<TaskDto>::class.java).toList()
            } else {
                throw Exception("Failed to fetch tasks: ${response.code}")
            }
        }
    }

    suspend fun addTask(taskDto: TaskDto): TaskDto = withContext(Dispatchers.IO) {
        val json = gson.toJson(taskDto)
        val requestBody = json.toRequestBody(jsonMediaType)
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/todos")
            .post(requestBody)
            .build()
        okHttpClient.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                gson.fromJson(response.body!!.string(), TaskDto::class.java) ?: taskDto
            } else {
                throw Exception("Failed to add task: ${response.code}")
            }
        }
    }

    suspend fun updateTask(taskDto: TaskDto): TaskDto = withContext(Dispatchers.IO) {
        val json = gson.toJson(taskDto)
        val requestBody = json.toRequestBody(jsonMediaType)
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/todos/${taskDto.id}")
            .put(requestBody)
            .build()
        okHttpClient.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                gson.fromJson(response.body!!.string(), TaskDto::class.java) ?: taskDto
            } else {
                throw Exception("Failed to update task: ${response.code}")
            }
        }
    }

    suspend fun deleteTask(taskId: Int): Unit = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/todos/$taskId")
            .delete()
            .build()
        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw Exception("Failed to delete task: ${response.code}")
            }
        }
    }
}