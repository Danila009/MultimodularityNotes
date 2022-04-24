package com.example.notes

import android.app.Application
import com.example.notes.di.DaggerAppComponent

class AppApplication:Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}