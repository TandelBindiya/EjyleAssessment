package com.bindiya.ejyleassessment.presentation.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bindiya.ejyleassessment.presentation.screens.CustomerOnBoarding
import com.bindiya.ejyleassessment.presentation.viewmodels.CustomerViewModel

@Composable
fun NavGraph(mainViewModel: CustomerViewModel= hiltViewModel()) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen.route
    ) {
        composable(route = AppScreen.HomeScreen.route) {
            CustomerOnBoarding(mainViewModel)
        }
    }
}