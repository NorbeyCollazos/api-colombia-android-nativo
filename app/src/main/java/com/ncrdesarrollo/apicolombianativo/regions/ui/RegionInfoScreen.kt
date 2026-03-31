package com.ncrdesarrollo.apicolombianativo.regions.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.departments.ui.RegionInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: RegionInfoViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val regionInfo by viewModel.infoRegion.collectAsState()


    val titleAppBar = when (val state = regionInfo) {
        UiState.Empty -> "Detalles"
        is UiState.Loading -> "Cargando..."
        is UiState.Error -> "Error"
        is UiState.Success -> {
            state.data?.name
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (titleAppBar != null) {
                        Text(text = titleAppBar)
                    }
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

            when (val state = regionInfo) {
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        InfoCard(
                            title = "Información de la Región",
                            icon = Icons.Default.Info,
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            state.data?.name?.let { name -> InfoRow("Nombre", name) }
                            state.data?.description?.let { description ->
                                InfoRow(
                                    "Descripción",
                                    description
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    title: String,
    icon: ImageVector,
    containerColor: androidx.compose.ui.graphics.Color,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            content()
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp)
    }
}