package com.example.triptrack.presentation.order_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun OrderScreen(
    viewModel: OrderViewModel,
) {
    val orders = viewModel.orders.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .padding(
                top = 30.dp,
                start = 30.dp,
                end = 30.dp,
            )
            .fillMaxSize(),
    ) {
        OrderList(orders = orders)
    }
}
