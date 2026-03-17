package com.ncrdesarrollo.apicolombianativo.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Info

@Serializable
object Departments

@Serializable
data class DepartmentInfo(val idDepartment : Int? = null)