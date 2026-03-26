package com.ncrdesarrollo.apicolombianativo.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ncrdesarrollo.apicolombianativo.departments.ui.DepartmentInfoScreen
import com.ncrdesarrollo.apicolombianativo.departments.ui.DepartmentsListScreen
import com.ncrdesarrollo.apicolombianativo.home.ui.HomeScreen
import com.ncrdesarrollo.apicolombianativo.info.ui.InfoScreen
import com.ncrdesarrollo.apicolombianativo.profile.ui.ProfileScreen
import com.ncrdesarrollo.apicolombianativo.saved.ui.SavedScreen

@Composable
fun NavigationWrapper(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {

        composable<Home> {
            HomeScreen(modifier = modifier, navController = navController){ route ->
                navController.navigate(route)
            }
        }

        composable<Info> {
            InfoScreen(modifier = modifier) {
                navController.popBackStack()
            }
        }

        composable<Departments> {
            DepartmentsListScreen(
                modifier = modifier,
                onClickItem = {navController.navigate(DepartmentInfo(it))},
                onBackClick = { navController.popBackStack() })

        }

        composable<DepartmentInfo> {
            DepartmentInfoScreen(
                modifier = modifier,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<Profile> {
            ProfileScreen(modifier = modifier)
        }

        composable<Saved> {
            SavedScreen()
        }

    }

}