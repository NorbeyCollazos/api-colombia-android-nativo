package com.ncrdesarrollo.apicolombianativo.regions.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.RegionModel
import com.ncrdesarrollo.apicolombianativo.regions.domain.IRegionsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RegionsViewModel @Inject constructor(
    private val interactor: IRegionsInteractor
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    val regionsState: StateFlow<UiState<List<RegionModel>>> =
        combine(
            interactor.getRegions(),
            _searchQuery
        ) { result, query ->
            result.fold(
                onSuccess = { departments ->
                    val filteredList = if (query.isEmpty()) {
                        departments
                    } else {
                        departments.filter { it.name?.contains(query, ignoreCase = true) == true }
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