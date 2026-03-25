package com.ncrdesarrollo.apicolombianativo.departments.data

import com.ncrdesarrollo.apicolombianativo.departments.domain.IDepartmentsRepository
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DepartmentsRepository @Inject constructor(private val dataSource: IDepartmentsDataSource) :
    IDepartmentsRepository {
    override fun getDepartments(): Flow<Result<List<DepartmentModel>>> {

        return dataSource.getDepartmentsLocal()
            .map { localDepartments ->
                Result.success(localDepartments.map { it.toDomain() })
            }
            .onStart {
                val responseApi = dataSource.getDepartments()
                    .mapCatching {
                        it.map { departmentDto ->
                            departmentDto.toDomain()
                        }
                    }
                responseApi.getOrNull()?.let { departments ->
                    dataSource.upsertDepartments(departments.map { it.toEntity() })
                }
            }
            .catch { e ->
                emit(Result.failure(exception = e))
            }
    }

    override suspend fun getDepartmentById(id: Int): Result<DepartmentModel?> =
        dataSource.getDepartmentById(id)
            .mapCatching {
                it?.toDomain()
            }

}