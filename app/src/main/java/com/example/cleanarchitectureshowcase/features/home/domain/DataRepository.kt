package com.example.cleanarchitectureshowcase.features.home.domain

import com.example.cleanarchitectureshowcase.features.home.data.DataDTO
import com.example.cleanarchitectureshowcase.features.home.data.StocksDTO

interface DataRepository {
    suspend fun getData(): DataDTO

    suspend fun getStocks(): List<StocksDTO>
}