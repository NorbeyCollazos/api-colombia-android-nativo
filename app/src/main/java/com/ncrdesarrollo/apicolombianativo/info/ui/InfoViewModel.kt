package com.ncrdesarrollo.apicolombianativo.info.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.apicolombianativo.core.utils.UiState
import com.ncrdesarrollo.apicolombianativo.info.domain.IInfoInteractor
import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(private val infoInteractor: IInfoInteractor) : ViewModel() {

    /*private var _infoLoadDataModel = MutableStateFlow<UiState<InfoModel>>(UiState.Loading)
    val infoLoadDataModel: MutableStateFlow<UiState<InfoModel>> = _infoLoadDataModel


    init {
        loadInfo()
    }

    private fun loadInfo() {

        viewModelScope.launch {
            try {
                _infoLoadDataModel.value = UiState.Success(infoInteractor.getInfo())
            } catch (e: Exception) {
                _infoLoadDataModel.value = UiState.Error(message = e.message ?: "Error desconocido")
            }
        }
    }*/

    val infoDataState: StateFlow<UiState<InfoModel>> = infoInteractor.getInfo()
        .distinctUntilChanged()
        .map { result ->
            result.fold(
                onSuccess = { infoModel ->
                    UiState.Success(infoModel)
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