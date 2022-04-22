package com.example.notes.navigation.host

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.core_utils.navigation.NoteConstants.NOTES_ROUTE
import com.example.notes.di.AppComponent
import com.example.notes.navigation.navHosts.notesListNavGraph

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun BaseHostNav(
    navHostController: NavHostController,
    appComponent: AppComponent
) {
    NavHost(
        navController = navHostController,
        startDestination = NOTES_ROUTE,
        route = "route",
        builder = {
            notesListNavGraph(
                navController = navHostController,
                appComponent = appComponent
            )
        }
    )
}