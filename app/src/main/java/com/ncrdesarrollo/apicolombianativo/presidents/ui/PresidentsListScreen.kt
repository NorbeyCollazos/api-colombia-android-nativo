package com.ncrdesarrollo.apicolombianativo.presidents.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncrdesarrollo.apicolombianativo.core.utils.SearchInputBar
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresidentsListScreen(
    modifier: Modifier = Modifier,
    viewModel: PresidentsViewModel = hiltViewModel(),
    onClickItem: (Int) -> Unit,
    onBackClick: () -> Unit
) {

    val presidentsState by viewModel.presidentsState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Presidentes") },
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
            when (val state = presidentsState) {
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
                                PresidentItem(
                                    name = "${president.name} ${president.lastName}",
                                    startPeriod = president.startPeriodDate?: "No disponible",
                                    endPeriod = president.endPeriodDate?: "No disponible",
                                    politicalParty = president.politicalParty?: "No disponible",
                                    description = president.description?: "No disponible",
                                    imageUrl = president.image?: "",
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}






