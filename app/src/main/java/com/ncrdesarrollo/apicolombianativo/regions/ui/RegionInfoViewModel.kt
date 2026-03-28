package com.ncrdesarrollo.apicolombianativo.departments.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ncrdesarrollo.apicolombianativo.core.navigation.RegionInfo
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.RegionModel
import com.ncrdesarrollo.apicolombianativo.regions.domain.IRegionsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegionInfoViewModel @Inject constructor(
    private val interactor: IRegionsInteractor,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val regionData = savedStateHandle.toRoute<RegionInfo>()

    private val _infoRegion = MutableStateFlow<UiState<RegionModel?>>(UiState.Loading)
    val infoRegion: StateFlow<UiState<RegionModel?>> = _infoRegion

    init {
        val idRegion = regionData.idRegion ?: 0

        viewModelScope.launch {
            val response = interactor.getRegionById(idRegion)
            response.onSuccess { region ->
                _infoRegion.value = UiState.Success(region)

            }
                .onFailure {
                    _infoRegion.value =
                        UiState.Error(message = it.message ?: "Error desconocido")
                }
        }
    }
}