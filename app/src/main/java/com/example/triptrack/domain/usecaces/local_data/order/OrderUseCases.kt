package com.example.triptrack.domain.usecaces.local_data.order // ktlint-disable package-name

data class OrderUseCases(
    val deleteOrder: DeleteOrder,
    val getOrderById: GetOrderById,
    val getOrderPaging: GetOrderPaging,
    val insertOrder: InsertOrder,
    val getOrderCount: OrderCount,
    val getCountOfOrdersMonth: GetCountOfOrdersMonth,
    val getAbsolutelyAllOrdersExceptForLast5Months: GetAbsolutelyAllOrdersExceptForLast5Months,
)
