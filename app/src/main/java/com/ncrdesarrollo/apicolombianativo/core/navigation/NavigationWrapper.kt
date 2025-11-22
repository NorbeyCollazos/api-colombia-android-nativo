package com.ncrdesarrollo.apicolombianativo.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ncrdesarrollo.apicolombianativo.home.ui.HomeScreen
import com.ncrdesarrollo.apicolombianativo.info.ui.InfoScreen
import com.ncrdesarrollo.apicolombianativo.info.ui.InfoViewModel

@Composable
fun NavigationWrapper(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {

        composable<Home> {
            HomeScreen(modifier = modifier) { route ->
                when (route) {
                    "info" -> navController.navigate(Info)

                }
            }
        }

        composable<Info> {
            val viewModelInfo: InfoViewModel = hiltViewModel()
            InfoScreen(modifier = modifier, viewModel = viewModelInfo) {
                navController.popBackStack()
            }
        }

    }

}