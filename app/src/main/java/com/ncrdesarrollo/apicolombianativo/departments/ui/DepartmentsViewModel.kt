package com.ncrdesarrollo.apicolombianativo.departments.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.departments.domain.IDepartmentsInteractor
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DepartmentsViewModel @Inject constructor(
    private val interactor: IDepartmentsInteractor
) : ViewModel() {

    val departmentsState: StateFlow<UiState<List<DepartmentModel>>> = interactor.getDepartments()
        .distinctUntilChanged()
        .map { result ->
            result.fold(
                onSuccess = { departments ->
                    UiState.Success(departments)
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