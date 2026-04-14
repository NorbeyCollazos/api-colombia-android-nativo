package com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTouristicAttractionsScreen(
    modifier: Modifier = Modifier,
    viewModel: InfoTouristicAttractionViewModel = hiltViewModel(),
    navigateToMap: (String, String, String, String) -> Unit,
    onBackClick: () -> Unit
) {

    val touristicAttractionInfo by viewModel.infoTouristicAttraction.collectAsStateWithLifecycle()


    val titleAppBar by remember(touristicAttractionInfo) {
        derivedStateOf {
            when (val state = touristicAttractionInfo) {
                UiState.Empty -> "Detalles"
                is UiState.Loading -> "Cargando..."
                is UiState.Error -> "Error"
                is UiState.Success -> {
                    state.data?.name
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    titleAppBar?.let { Text(text = it) }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (val state = touristicAttractionInfo) {
                UiState.Empty -> {
                    Text(text = "No hay información disponible")
                }

                is UiState.Error -> {
                    Text(text = state.message)
                }

                UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is UiState.Success -> {
                    state.data?.let { data ->
                        Information(
                            images = data.images ?: emptyList(),
                            imageLoader = viewModel.imageLoader,
                            name = data.name ?: "",
                            latitude = data.latitude ?: "",
                            longitude = data.longitude ?: "",
                            description = data.description ?: "",
                            navigateToMap = { navigateToMap(data.latitude ?: "", data.longitude ?: "", data.name ?: "", data.description ?: "") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Information(
    images: List<String>,
    imageLoader: ImageLoader,
    name: String,
    latitude: String,
    longitude: String,
    description: String,
    navigateToMap: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CarouselImages(
            images = images,
            imageLoader = imageLoader
        )
        InformationBody(
            name = name,
            latitude = latitude,
            longitude = longitude,
            description = description
        ) {
            navigateToMap()
        }

    }
}

@Composable
fun CarouselImages(images: List<String>, imageLoader: ImageLoader) {

    LaunchedEffect(images) {
        Log.i("images", "Las imágenes han cambiado o se han cargado: ${images.size}")
    }

    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        if (images.isNotEmpty()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page])
                        .crossfade(true)
                        .build(),
                    imageLoader = imageLoader,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Indicador de puntos (Dots) para el carrusel
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(images.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.White else Color.LightGray.copy(
                            alpha = 0.5f
                        )
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        } else {
            // Placeholder si no hay imágenes
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay imágenes disponibles", color = Color.White)
            }
        }
    }
}

@Composable
fun InformationBody(
    name: String,
    latitude: String,
    longitude: String,
    description: String,
    navigateToMap: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Tittle(name)

        Spacer(modifier = Modifier.height(12.dp))

        Coordinator(latitude, longitude) { navigateToMap() }

        Spacer(modifier = Modifier.height(24.dp))

        Description(description)

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun Tittle(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun Coordinator(latitude: String, longitude: String, navigateToMap: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToMap() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Ubicación",
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Coordenadas",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Lat: $latitude | Long: $longitude",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun Description(description: String) {
    Text(
        text = "Descripción",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
        lineHeight = 24.sp,
        textAlign = androidx.compose.ui.text.style.TextAlign.Justify
    )
}

@Composable
@Preview(showBackground = true)
fun InformationBodyPreview() {
    val context = LocalContext.current
    val dummyImageLoader = ImageLoader.Builder(context).build()

    MaterialTheme {
        Information(
            images = listOf(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Edificio_el_C%C3%B3ndor.jpg/220px-Edificio_el_C%C3%B3ndor.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Catedral_de_Sal_de_Zipaquira.jpg/250px-Catedral_de_Sal_de_Zipaquira.jpg"
            ),
            imageLoader = dummyImageLoader,
            name = "Puente las Damas",
            latitude = "2.4448",
            longitude = "-76.6147",
            description = "Un sitio histórico muy importante de la ciudad de Garzón...",
        ) {}
    }
}




