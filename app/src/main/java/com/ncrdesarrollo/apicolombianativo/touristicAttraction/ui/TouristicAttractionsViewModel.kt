package com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.domain.ITouristicAttractionsInteractor
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui.model.TouristicAttractionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TouristicAttractionsViewModel @Inject constructor(
    private val interactor: ITouristicAttractionsInteractor,
    val imageLoader: ImageLoader
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }


    val touristicAttractionsState: StateFlow<UiState<List<TouristicAttractionModel>>> =
        combine(
            interactor.getTouristicAttractions(),
            _searchQuery
        ) { result, query ->
            result.fold(
                onSuccess = { touristicAttractions ->
                    val filteredList = if (query.isEmpty()) {
                        touristicAttractions
                    } else {
                        touristicAttractions.filter {
                            it.name?.contains(
                                query,
                                ignoreCase = true
                            ) == true
                        }
                    }
                    UiState.Success(filteredList)
                },
                onFailure = {
                    UiState.Error(message = it.message ?: "Error desconocido")
                }

            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Loading
            )
}