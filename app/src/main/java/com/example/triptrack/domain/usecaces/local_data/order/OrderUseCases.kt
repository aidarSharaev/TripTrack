package com.example.triptrack.domain.usecaces.local_data.order // ktlint-disable package-name

data class OrderUseCases(
    val deleteOrder: DeleteOrder,
    val getOrderById: GetOrderById,
    val getOrderPaging: GetOrderPaging,
    val insertOrder: InsertOrder,
    val orderCount: OrderCount,
)
