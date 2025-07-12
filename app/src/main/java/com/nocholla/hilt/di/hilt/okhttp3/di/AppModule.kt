package com.nocholla.hilt.di.hilt.okhttp3.di

import com.nocholla.hilt.di.hilt.okhttp3.data.api.OkHttpClientFactory
import com.nocholla.hilt.di.hilt.okhttp3.data.api.TaskApiService
import com.nocholla.hilt.di.hilt.okhttp3.data.repository.TaskRepositoryImpl
import com.nocholla.hilt.di.hilt.okhttp3.domain.repository.TaskRepository
import com.nocholla.hilt.di.hilt.okhttp3.domain.usecase.*
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpClientFactory: OkHttpClientFactory): OkHttpClient {
        return okHttpClientFactory.createOkHttpClient()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideTaskApiService(okHttpClient: OkHttpClient, gson: Gson): TaskApiService {
        return TaskApiService(okHttpClient, gson)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskApiService: TaskApiService): TaskRepository {
        return TaskRepositoryImpl(taskApiService)
    }

    @Provides
    fun provideAddTaskUseCase(repository: TaskRepository): AddTaskUseCase {
        return AddTaskUseCase(repository)
    }

    @Provides
    fun provideGetTasksUseCase(repository: TaskRepository): GetTasksUseCase {
        return GetTasksUseCase(repository)
    }

    @Provides
    fun provideToggleTaskCompletionUseCase(repository: TaskRepository): ToggleTaskCompletionUseCase {
        return ToggleTaskCompletionUseCase(repository)
    }

    @Provides
    fun provideRemoveTaskUseCase(repository: TaskRepository): RemoveTaskUseCase {
        return RemoveTaskUseCase(repository)
    }
}