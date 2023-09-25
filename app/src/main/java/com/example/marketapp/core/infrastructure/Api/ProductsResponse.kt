package com.example.marketapp.core.infrastructure.Api

data class ProductsResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)