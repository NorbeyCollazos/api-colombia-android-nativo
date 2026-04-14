package com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import coil.ImageLoader
import com.ncrdesarrollo.apicolombianativo.core.navigation.TouristicSitesInfo
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.domain.ITouristicAttractionsInteractor
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui.model.TouristicAttractionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoTouristicAttractionViewModel @Inject constructor(
    private val interactor: ITouristicAttractionsInteractor,
    savedStateHandle: SavedStateHandle,
    val imageLoader: ImageLoader
) : ViewModel() {

    private val touristicAttractionsData = savedStateHandle.toRoute<TouristicSitesInfo>()

    private val _infoTouristicAttraction =
        MutableStateFlow<UiState<TouristicAttractionModel?>>(UiState.Loading)
    val infoTouristicAttraction: StateFlow<UiState<TouristicAttractionModel?>> =
        _infoTouristicAttraction

    init {
        val idTouristicAttraction = touristicAttractionsData.idSiteTouristic ?: 0

        viewModelScope.launch {
            val response = interactor.getTouristicAttractionById(idTouristicAttraction)
            response.onSuccess { touristicAttractions ->
                _infoTouristicAttraction.value = UiState.Success(touristicAttractions)

            }
                .onFailure {
                    _infoTouristicAttraction.value =
                        UiState.Error(message = it.message ?: "Error desconocido")
                }
        }
    }
}