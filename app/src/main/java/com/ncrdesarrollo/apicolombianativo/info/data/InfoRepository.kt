package com.ncrdesarrollo.apicolombianativo.info.data


import com.ncrdesarrollo.apicolombianativo.info.domain.IInfoRepository
import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class InfoRepository @Inject constructor(private val infoDataSource: IInfoDataSource) :
    IInfoRepository {
    override fun getInfo(): Flow<Result<InfoModel>> {

        return infoDataSource.getInfoLocal()
            .map { info ->
                Result.success(info.toDomain())
            }
            .onStart {
                val responseApi = infoDataSource.getInfo()
                    .mapCatching {
                        it.toDomain()
                    }
                responseApi.getOrNull()?.let { info ->
                    infoDataSource.saveLocalInfo(info.toEntity())
                }
            }
            .catch { e ->
                emit(Result.failure(exception = e))
            }

    }

}