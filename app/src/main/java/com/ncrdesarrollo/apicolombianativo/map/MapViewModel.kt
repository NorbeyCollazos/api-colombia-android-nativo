package com.ncrdesarrollo.apicolombianativo.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.ncrdesarrollo.apicolombianativo.core.navigation.Map
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mapData = savedStateHandle.toRoute<Map>()

    val latitude = mapData.latitude.toDouble()
    val longitude = mapData.longitude.toDouble()
    val title = mapData.title
    val snippet = mapData.snippet

}