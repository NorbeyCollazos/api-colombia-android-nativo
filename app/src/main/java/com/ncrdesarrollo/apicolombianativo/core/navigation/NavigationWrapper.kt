package com.ncrdesarrollo.apicolombianativo.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ncrdesarrollo.apicolombianativo.departments.ui.DepartmentInfoScreen
import com.ncrdesarrollo.apicolombianativo.departments.ui.DepartmentsListScreen
import com.ncrdesarrollo.apicolombianativo.departments.ui.DepartmentsViewModel
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
                    "departments" -> navController.navigate(Departments)
                }
            }
        }

        composable<Info> {
            val viewModelInfo: InfoViewModel = hiltViewModel()
            InfoScreen(modifier = modifier, viewModel = viewModelInfo) {
                navController.popBackStack()
            }
        }

        composable<Departments> {
            val viewModelDepartments: DepartmentsViewModel = hiltViewModel()
            DepartmentsListScreen(
                modifier = modifier,
                viewModel = viewModelDepartments,
                onClickItem = {navController.navigate(DepartmentInfo(it))},
                onBackClick = { navController.popBackStack() })

        }

        composable<DepartmentInfo> {
            val viewModelDepartments: DepartmentsViewModel = hiltViewModel()
            DepartmentInfoScreen(
                modifier = modifier,
                viewModel = viewModelDepartments,
                onBackClick = { navController.popBackStack() }
            )
        }

    }

}