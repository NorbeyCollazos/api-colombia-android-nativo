package com.ncrdesarrollo.apicolombianativo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ncrdesarrollo.apicolombianativo.core.navigation.NavigationWrapper
import com.ncrdesarrollo.apicolombianativo.home.ui.HomeScreen
import com.ncrdesarrollo.apicolombianativo.info.ui.InfoScreen
import com.ncrdesarrollo.apicolombianativo.info.ui.InfoViewModel
import com.ncrdesarrollo.apicolombianativo.ui.theme.ApiColombiaNativoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModelInfo: InfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiColombiaNativoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    NavigationWrapper(modifier = Modifier.padding(innerPadding))
                }
            }
        }

    }
}

@Composable
fun AppNavigation() {
    // 1. Crea un NavController para gestionar la navegación
    val navController = rememberNavController()

    // 2. Configura el NavHost, que es el contenedor de tus pantallas
    NavHost(
        navController = navController,
        startDestination = "home" // Define la pantalla inicial
    ) {
        // 3. Define la ruta "home" y el Composable que se mostrará
        /*composable("home") {
            HomeScreen(
                onOptionClick = { route ->
                    // 4. Aquí manejas la lógica de navegación cuando se hace clic en una opción
                    Log.d("Navegacion", "Navegando a la ruta: $route")
                    // Cuando tengas otras pantallas, las llamarás así:
                    // navController.navigate(route)
                }
            )
        }*/

        // Aquí puedes añadir otras pantallas en el futuro
        /*
        composable("info") {
            InfoScreen() // Suponiendo que tienes un InfoScreen
        }
        composable("departments") {
            // Composable para la pantalla de departamentos
        }
        */
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiColombiaNativoTheme {
        Greeting("Android")
    }
}