package com.ncrdesarrollo.apicolombianativo.info.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ncrdesarrollo.apicolombianativo.R
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoDetail
import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel
import com.ncrdesarrollo.apicolombianativo.ui.theme.ApiColombiaNativoTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    viewModel: InfoViewModel,
    onNavigateBack: () -> Unit
) {
    val info by viewModel.infoDataState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Información") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                })

        }
    ) { innerPadding ->

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = info) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }

                is UiState.Success -> {
                    InfoContentView(info = state.data, Modifier.padding(innerPadding))
                }

                is UiState.Error -> {
                    Text("Error al cargar la información")
                }

                is UiState.Empty -> {
                    Text("No hay información disponible")
                }
            }
        }

    }

}

@Composable
fun InfoContentView(info: InfoModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()

    ) {

        val details = listOfNotNull(
            info.stateCapital?.let { InfoDetail(R.drawable.ic_capital, "Capital", it) },
            info.surface?.let { InfoDetail(R.drawable.ic_surface, "Superficie", "$it km²") },
            info.population?.let {
                InfoDetail(
                    R.drawable.ic_population,
                    "Población",
                    it.toString()
                )
            },
            info.timeZone?.let { InfoDetail(R.drawable.ic_timezone, "Zona Horaria", it) },
            info.currencyCode?.let { InfoDetail(R.drawable.ic_currency, "Moneda", it) },
            info.region?.let { InfoDetail(R.drawable.ic_surface, "Región", it) }
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitleText(title = info.name ?: "No disponible")

            Spacer(modifier = Modifier.height(16.dp))

            ImageFlag(flags = info.flags)

            Spacer(modifier = Modifier.height(16.dp))

            CardsInfo(details)

            Spacer(modifier = Modifier.height(16.dp))

            DescriptionText(description = info.description ?: "No disponible")

        }
    }
}

@Composable
fun DescriptionText(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify
    )
}

@Composable
fun CardsInfo(details: List<InfoDetail>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(details) { detail ->
            InfoDetailCard(
                icon = painterResource(id = getIconForTitle(detail.title)),
                title = detail.title,
                value = detail.value
            )
        }
    }
}

@Composable
fun ImageFlag(flags: List<String>?) {
    if (flags != null) {
        Image(
            painter = rememberAsyncImagePainter(
                model = flags.lastOrNull(),
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            ),
            contentDescription = "Bandera de Colombia",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TitleText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center
    )
}

// Función auxiliar para obtener el recurso drawable del ícono
@Composable
private fun getIconForTitle(title: String): Int {
    return when (title) {
        "Capital" -> R.drawable.ic_capital
        "Superficie" -> R.drawable.ic_surface
        "Población" -> R.drawable.ic_population
        "Zona Horaria" -> R.drawable.ic_timezone
        "Moneda" -> R.drawable.ic_currency
        "Región" -> R.drawable.ic_surface
        else -> R.drawable.ic_launcher_foreground
    }
}

@Composable
fun InfoDetailCard(icon: Painter, title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                modifier = Modifier.size(28.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoContentViewPreview() {
    ApiColombiaNativoTheme {

        val previewInfo = InfoModel(
            name = "Colombia (Preview)",
            description = "Colombia, oficialmente República de Colombia, es un país soberano situado en la región noroccidental de América del Sur...",
            flags = listOf("https://..."),
            stateCapital = "Bogotá",
            surface = 1141748,
            population = 50372424,
            timeZone = "UTC-5",
            currencyCode = "COP",
            region = "Andina"
        )

        InfoContentView(info = previewInfo)

    }
}
