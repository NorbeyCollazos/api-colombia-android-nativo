package com.ncrdesarrollo.apicolombianativo.presidents.data

import com.ncrdesarrollo.apicolombianativo.presidents.domain.IPresidentsRepository
import com.ncrdesarrollo.apicolombianativo.presidents.ui.model.PresidentModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PresidentsRepository @Inject constructor(private val dataSource: IPresidentsDataSource) :
    IPresidentsRepository {
    override fun getPresidents(): Flow<Result<List<PresidentModel>>> {

        return dataSource.getPresidentsLocal()
            .map { localPresidents ->
                Result.success(localPresidents.map { it.toDomain() })
            }
            .onStart {
                val responseApi = dataSource.getPresidents()
                    .mapCatching {
                        it.map { presidentDto ->
                            presidentDto.toDomain()
                        }
                    }
                responseApi.getOrNull()?.let { presidents ->
                    dataSource.upsertPresidents(presidents.map { it.toEntity() })
                }
            }
            .catch { e ->
                emit(Result.failure(exception = e))
            }
    }

    override suspend fun getPresidentById(id: Int): Result<PresidentModel?> =
        dataSource.getPresidentById(id)
            .mapCatching {
                it?.toDomain()
            }

}