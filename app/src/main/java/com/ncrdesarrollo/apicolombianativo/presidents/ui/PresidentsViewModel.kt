package com.ncrdesarrollo.apicolombianativo.presidents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.presidents.domain.IPresidentsInteractor
import com.ncrdesarrollo.apicolombianativo.presidents.ui.model.PresidentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PresidentsViewModel @Inject constructor(
    private val interactor: IPresidentsInteractor
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    val presidentsState: StateFlow<UiState<List<PresidentModel>>> =
        combine(
            interactor.getPresidents(),
            _searchQuery
        ) { result, query ->
            result.fold(
                onSuccess = { presidents ->
                    val filteredList = if (query.isEmpty()) {
                        presidents
                    } else {
                        presidents.filter {
                            it.name?.contains(
                                query,
                                ignoreCase = true
                            ) == true || it.lastName?.contains(query, ignoreCase = true) == true
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