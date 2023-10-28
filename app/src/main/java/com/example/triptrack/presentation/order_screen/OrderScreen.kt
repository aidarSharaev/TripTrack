package com.example.triptrack.presentation.order_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.triptrack.model.Order

@Composable
fun OrderScreen(
  orders: LazyPagingItems<Order>
) {

  Column(
    modifier = Modifier
      .padding(
        top = 30.dp,
        start = 30.dp,
        end = 30.dp
      )
      .fillMaxSize()
  ) {
    OrderList(orders = orders)
  }

}

//@Composable
//@Preview(showBackground = true)
//fun F() {
//  OrderScreen(
//    Order(id = 123, route = "Челны", payment = true, tax = true, date = "23-01-2003", income = 15000, wastes = 3000, profit = -12000) as LazyPagingItems<Order>
//  )
//}


//TODO(REPLACE ARGS)




