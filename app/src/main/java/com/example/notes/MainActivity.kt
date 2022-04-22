package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.example.notes.di.AppComponent
import com.example.notes.di.DaggerAppComponent
import com.example.notes.navigation.host.BaseHostNav

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
        setContent {
            BaseHostNav(
                appComponent = appComponent,
                navHostController = rememberNavController()
            )
        }
    }
}
