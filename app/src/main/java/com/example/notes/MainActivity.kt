package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import com.example.core_ui.NotesTheme
import com.example.notes.di.AppComponent
import com.example.notes.di.DaggerAppComponent
import com.example.notes.navigation.host.BaseHostNav

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
        setContent {
            NotesTheme {
                BaseHostNav(
                    appComponent = appComponent,
                    navHostController = rememberNavController()
                )
            }
        }
    }
}
