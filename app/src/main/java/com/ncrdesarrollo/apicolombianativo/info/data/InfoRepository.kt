package com.ncrdesarrollo.apicolombianativo.info.data


import com.ncrdesarrollo.apicolombianativo.info.domain.IInfoRepository
import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel
import org.json.JSONObject
import javax.inject.Inject

class InfoRepository @Inject constructor(private val infoDataSource: IInfoDataSource) :
    IInfoRepository {
    override suspend fun getInfo(): InfoModel {

        val response = infoDataSource.getInfo()

        var model = InfoModel()

        if (response.isSuccessful) {
            response.body()?.string()?.let { responseString ->
                model = setJsonToModel(JSONObject(responseString))
            }
        }

        return model
    }

    private fun setJsonToModel(jsonObject: JSONObject): InfoModel {

        val bordersJson = jsonObject.optJSONArray("borders")
        val bordersList = mutableListOf<String>()
        if (bordersJson != null) {
            for (i in 0 until bordersJson.length()) {
                bordersList.add(bordersJson.getString(i))
            }
        }

        val flagsJson = jsonObject.optJSONArray("flags")
        val flagsList = mutableListOf<String>()
        if (flagsJson != null) {
            for (i in 0 until flagsJson.length()) {
                flagsList.add(flagsJson.getString(i))
            }
        }

        val languagesJson = jsonObject.optJSONArray("languages")
        val languagesList = mutableListOf<String>()
        if (languagesJson != null) {
            for (i in 0 until languagesJson.length()) {
                languagesList.add(languagesJson.getString(i))
            }
        }

        return InfoModel(
            id = jsonObject.optInt("id"),
            name = jsonObject.optString("name"),
            description = jsonObject.optString("description"),
            stateCapital = jsonObject.optString("stateCapital"),
            surface = jsonObject.optInt("surface"),
            population = jsonObject.optInt("population"),
            languages = languagesList.ifEmpty { null },
            timeZone = jsonObject.optString("timeZone"),
            currency = jsonObject.optString("currency"),
            currencyCode = jsonObject.optString("currencyCode"),
            currencySymbol = jsonObject.optString("currencySymbol"),
            isoCode = jsonObject.optString("isoCode"),
            internetDomain = jsonObject.optString("internetDomain"),
            phonePrefix = jsonObject.optString("phonePrefix"),
            radioPrefix = jsonObject.optString("radioPrefix"),
            aircraftPrefix = jsonObject.optString("aircraftPrefix"),
            subRegion = jsonObject.optString("subRegion"),
            region = jsonObject.optString("region"),
            borders = bordersList.ifEmpty { null },
            flags = flagsList.ifEmpty { null }
        )
    }


}