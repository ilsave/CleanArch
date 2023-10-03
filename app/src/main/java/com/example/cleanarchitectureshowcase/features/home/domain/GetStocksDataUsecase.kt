package com.example.cleanarchitectureshowcase.features.home.domain

import com.example.cleanarchitectureshowcase.core.domain.CoroutinesUseCase
import com.example.cleanarchitectureshowcase.features.home.data.StocksDTO
import javax.inject.Inject

class GetStocksDataUsecase @Inject constructor(
    private val repository: DataRepository
): CoroutinesUseCase<String, List<StocksDTO>> {

    // я не обрабатываю полученные данные в бизнес-логике, передаю прям так List<StocksDTO>
    // не уверен, что StocksDTO из даты можно вот так подключить к домену как я сделал
    override suspend fun invoke(params: String): List<StocksDTO> {
        return repository.getStocks()
    }
}
