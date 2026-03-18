package com.ncrdesarrollo.apicolombianativo.departments.data

import com.ncrdesarrollo.apicolombianativo.departments.domain.IDepartmentsRepository
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.CityCapital
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class DepartmentsRepository @Inject constructor(private val dataSource: IDepartmentsDataSource) :
    IDepartmentsRepository {
    override suspend fun getDepartments(): List<DepartmentModel> {
        val listDepartments: MutableList<DepartmentModel> = mutableListOf()
        val response = dataSource.getDepartments()

        val responseString: String? = response.body()?.string() ?: response.errorBody()?.string()
        if (response.isSuccessful && responseString != null) {
            val jsonArray = JSONArray(responseString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val departmentModel = DepartmentModel(
                    id = jsonObject.getInt("id"),
                    name = jsonObject.getString("name"),
                    description = jsonObject.getString("description"),
                )
                listDepartments.add(departmentModel)
            }
        }

        return listDepartments
    }

    override suspend fun getDepartmentById(id: Int): DepartmentModel {
        var departmentModel = DepartmentModel()
        val response = dataSource.getDepartmentById(id)

        if (response.isSuccessful) {
            response.body()?.string()?.let { responseString ->
                val jsonObject = JSONObject(responseString)
                departmentModel = setJsonToModel(jsonObject)
            }
        }

        return departmentModel

    }

    private fun setJsonToModel(jsonObject: JSONObject): DepartmentModel {

        val jsonCityCapital = jsonObject.getJSONObject("cityCapital")

        return DepartmentModel(
            id = jsonObject.optInt("id"),
            name = jsonObject.optString("name"),
            description = jsonObject.optString("description"),
            surface = jsonObject.optInt("surface"),
            population = jsonObject.optInt("population"),
            cityCapital = CityCapital(
                id = jsonCityCapital.optInt("id"),
                name = jsonCityCapital.optString("name"),
                description = jsonCityCapital.optString("description"),
                surface = jsonCityCapital.optInt("surface"),
                population = jsonCityCapital.optInt("population"),
                postalCode = jsonCityCapital.optString("postalCode"),
            )

        )
    }

}