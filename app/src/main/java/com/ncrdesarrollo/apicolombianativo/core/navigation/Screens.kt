package com.ncrdesarrollo.apicolombianativo.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Profile

@Serializable
object Saved

@Serializable
object Info

@Serializable
object Departments

@Serializable
object Presidents

@Serializable
object Regions

@Serializable
data class RegionInfo(val idRegion: Int? = null)

@Serializable
object TouristicSites

@Serializable
data class DepartmentInfo(val idDepartment: Int? = null)