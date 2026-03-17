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
class DepartmentsViewModel @Inject constructor(
    private val interactor: IDepartmentsInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val departmentData = savedStateHandle.toRoute<DepartmentInfo>()

    private val _departmentsState =
        MutableStateFlow<UiState<List<DepartmentModel>>>(UiState.Loading)
    val departmentsState: StateFlow<UiState<List<DepartmentModel>>> = _departmentsState

    private val _infoDepartment = MutableStateFlow<UiState<DepartmentModel>>(UiState.Loading)
    val infoDepartment: StateFlow<UiState<DepartmentModel>> = _infoDepartment


    init {
        getDepartments()
    }


    private fun getDepartments() {
        try {
            viewModelScope.launch {
                val response = interactor.getDepartments()
                _departmentsState.value = UiState.Success(response)
            }
        } catch (e: Exception) {
            _departmentsState.value = UiState.Error(message = e.message ?: "Error desconocido")
        }
    }

    fun getDepartmentInfo() {
        val idDepartment = departmentData.idDepartment ?: 0

        try {
            viewModelScope.launch {
                val response = interactor.getDepartmentById(idDepartment)
                _infoDepartment.value = UiState.Success(response)
            }
        } catch (e: Exception) {
            _infoDepartment.value = UiState.Error(message = e.message ?: "Error desconocido")
        }
    }

}