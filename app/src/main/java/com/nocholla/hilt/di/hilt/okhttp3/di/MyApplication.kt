package com.nocholla.hilt.di.hilt.okhttp3.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    // This class enables Hilt for your application
}