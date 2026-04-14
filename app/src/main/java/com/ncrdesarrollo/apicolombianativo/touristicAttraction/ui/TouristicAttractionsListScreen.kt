package com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ncrdesarrollo.apicolombianativo.R
import com.ncrdesarrollo.apicolombianativo.core.utils.SearchInputBar
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouristicAttractionsListScreen(
    modifier: Modifier = Modifier,
    viewModel: TouristicAttractionsViewModel = hiltViewModel(),
    onClickItem: (Int) -> Unit,
    onBackClick: () -> Unit
) {

    val touristicAttractionsState by viewModel.touristicAttractionsState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val imageLoader = viewModel.imageLoader

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Atracciones turísticas") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
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
            when (val state = touristicAttractionsState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                UiState.Empty -> {
                    Text(
                        text = "No hay presidentes disponibles",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UiState.Error -> {
                    Text(
                        text = state.message,
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Red
                    )
                }

                is UiState.Success -> {
                    Column {
                        SearchInputBar(searchQuery) { viewModel.onSearchQueryChange(it) }
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(state.data) { president ->
                                TouristicAttractionItem(
                                    president.images?.first() ?: "", imageLoader, president.name
                                        ?: ""
                                ) { president.id?.let { onClickItem(it) } }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TouristicAttractionItem(
    imageUrl: String,
    imageLoader: ImageLoader,
    name: String,
    onClickItem: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickItem() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                placeholder = painterResource(R.drawable.turistic_sites),
                error = painterResource(R.drawable.turistic_sites),
                contentDescription = "Foto de $name",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = name,
                modifier = Modifier
                    .padding(start = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}






