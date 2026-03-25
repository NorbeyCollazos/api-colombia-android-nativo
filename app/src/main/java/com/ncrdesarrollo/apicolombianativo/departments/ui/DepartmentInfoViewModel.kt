package com.ncrdesarrollo.apicolombianativo.departments.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ncrdesarrollo.apicolombianativo.core.navigation.DepartmentInfo
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.departments.domain.IDepartmentsInteractor
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentInfoViewModel @Inject constructor(
    private val interactor: IDepartmentsInteractor,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val departmentData = savedStateHandle.toRoute<DepartmentInfo>()

    private val _infoDepartment = MutableStateFlow<UiState<DepartmentModel?>>(UiState.Loading)
    val infoDepartment: StateFlow<UiState<DepartmentModel?>> = _infoDepartment

    init {
        val idDepartment = departmentData.idDepartment ?: 0

        viewModelScope.launch {
            val response = interactor.getDepartmentById(idDepartment)
            response.onSuccess { department ->
                _infoDepartment.value = UiState.Success(department)

            }
                .onFailure {
                    _infoDepartment.value =
                        UiState.Error(message = it.message ?: "Error desconocido")
                }
        }
    }
}