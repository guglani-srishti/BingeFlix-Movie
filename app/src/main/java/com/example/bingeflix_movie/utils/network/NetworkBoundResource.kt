package com.example.bingeflix_movie.helper

import com.example.bingeflix_movie.utils.network.DataState
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(DataState.Loading)

        try {
            saveFetchResult(fetch())
            query().map { DataState.Success(it) }
        } catch (e: Exception) {
            query().map { DataState.Error(e) }
        }
    } else {
        query().map { DataState.Success(it) }
    }

    emitAll(flow)
}