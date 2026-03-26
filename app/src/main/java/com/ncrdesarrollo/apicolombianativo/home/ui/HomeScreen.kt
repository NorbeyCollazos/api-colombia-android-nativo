package com.ncrdesarrollo.apicolombianativo.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ncrdesarrollo.apicolombianativo.R
import com.ncrdesarrollo.apicolombianativo.core.navigation.Departments
import com.ncrdesarrollo.apicolombianativo.core.navigation.Info
import com.ncrdesarrollo.apicolombianativo.core.navigation.Presidents
import com.ncrdesarrollo.apicolombianativo.core.navigation.Regions
import com.ncrdesarrollo.apicolombianativo.core.navigation.TouristicSites
import com.ncrdesarrollo.apicolombianativo.home.ui.models.HomeOption
import com.ncrdesarrollo.apicolombianativo.home.ui.navigation.BottomNavigationBar
import com.ncrdesarrollo.apicolombianativo.ui.theme.ApiColombiaNativoTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onOptionCardClick: (Any) -> Unit,
) {
    Scaffold(
        /*topBar = {
            TopAppBar(
                title = { Text("Api Colombia Nativo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },*/
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        modifier = Modifier
    ) { innerPadding ->
        HomeContent(
            onOptionClick = onOptionCardClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeBanner(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(top = 25.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.icono_regiones),
                contentDescription = "Banner de Colombia",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = "Descubre Colombia",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .background(Color.White)
            )
        }
    }
}

@Composable
fun HomeContent(onOptionClick: (Any) -> Unit, modifier: Modifier = Modifier) {

    val options = listOf(
        HomeOption("Información", painterResource(R.drawable.info), Info),
        HomeOption(
            "Departamentos",
            painterResource(R.drawable.departments),
            Departments
        ),
        HomeOption("Regiones", painterResource(R.drawable.regions), Regions),
        HomeOption(
            "Presidentes",
            painterResource(R.drawable.president),
            Presidents
        ),
        HomeOption(
            "Sitios Turísticos",
            painterResource(R.drawable.turistic_sites),
            TouristicSites
        )
    )


    Column(modifier = Modifier) {

        HomeBanner(modifier = Modifier.padding(16.dp))

        Text(
            text = "Categorias",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(options) { option ->
                HomeOptionCard(option = option, onClick = { onOptionClick(option.route) })
            }
        }
    }
}


@Composable
fun HomeOptionCard(option: HomeOption, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = option.icon,
                contentDescription = option.title,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = option.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ApiColombiaNativoTheme {
        HomeScreen(navController = NavController(LocalContext.current), onOptionCardClick = {})
    }
}