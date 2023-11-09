package com.example.triptrack.presentation.order_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.triptrack.R
import com.example.triptrack.model.Order
import com.example.triptrack.ui.theme.TripTrackTheme
import com.example.triptrack.ui.theme.fontBold
import com.example.triptrack.ui.theme.fontItalic

@Composable
fun OrderList(
  orders: LazyPagingItems<Order>
) {
  val handlePagingResult = handlePagingResult(orders = orders)
  if(handlePagingResult) {
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(all = 6.dp)
    ) {
      items(count = orders.itemCount) { it ->
        orders[it]?.let {
          OrderCard(order = it)
        }
      }
    }
  }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun OrderCardPreview() {
  OrderCard(
    Order(
      id = 123,
      route = "Челны",
      payment = true,
      tax = true,
      date = "23-01-2003",
      income = 15000,
      wastes = 3000,
      profit = -12000
    )
  )
  //OrderCardShimmerEffect()
}


val CardHeight = 128.dp
val textSize = 18.sp
val textSize2 = 17.sp


@Composable
fun OrderCard(order: Order) {

  val textColor = if(isSystemInDarkTheme()) Color.White
  else Color.Black

  val moneyColor = if(order.profit < 0) Color.Red
  else colorResource(id = R.color.order_info)

  val textWhere = buildAnnotatedString {
    withStyle(
      style = SpanStyle(
        color = textColor,
        fontSize = textSize,
        fontFamily = fontBold
      )
    ) {
      append("Маршрут: ")
    }
    withStyle(
      style = SpanStyle(
        color = textColor,
        fontFamily = fontItalic,
        fontSize = textSize
      )
    ) {
      append("\t ${order.route}")
    }
  }

  val textEmployer = buildAnnotatedString {
    withStyle(
      style = SpanStyle(
        fontFamily = fontBold,
        color = textColor,
        fontSize = textSize,
      )
    ) {
      append("От кого: ")
    }
    withStyle(
      style = SpanStyle(
        color = textColor,
        fontFamily = fontItalic,
        fontSize = textSize
      )
    ) {
      append("\t ${order.employerDescription ?: "Хер знает"}")
    }
  }

  val textInfo = buildAnnotatedString {
    withStyle(
      style = SpanStyle(
        color = moneyColor,
        fontFamily = fontBold,
        fontSize = textSize2
        )
    ) {
      append("${order.profit} \t")
    }
    withStyle(
      style = SpanStyle(
        color = textColor,
        fontFamily = fontBold,
        fontSize = textSize2
      )
    ) {
      append("${order.date} \t Налог")
    }
  }

  Row(
    modifier = Modifier
      .clip(RoundedCornerShape(20.dp))
      .background(
        if(isSystemInDarkTheme()) colorResource(id = R.color.order_card_night)
        else colorResource(id = R.color.order_card)
      )
      .height(CardHeight)
      .padding(all = 8.dp)
      .fillMaxWidth()
  ) {
    Row(Modifier.fillMaxSize()) {
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
          .fillMaxHeight()
          .width(50.dp)
      ) {
        Text(
          text = "${order.id}",
          fontFamily = fontBold,
          fontSize = textSize,
          color = textColor
        )
      }
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(top = 10.dp, start = 6.dp),
        horizontalAlignment = Alignment.Start
      ) {
        Row {
          Text(text = textWhere)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
          Text(text = textEmployer)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = textInfo)
          Spacer(modifier = Modifier.width(2.dp))
          Icon(
            painter = painterResource(id = R.drawable.done), contentDescription = null,
            tint = Color.Green
          )
        }
      }
    }
  }
  Spacer(Modifier.height(8.dp))
}


@Composable
fun handlePagingResult(
  orders: LazyPagingItems<Order>
): Boolean {

  val loadState = orders.loadState
  val error = when {
    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
    else -> null
  }


  return when {
    loadState.refresh is LoadState.Loading -> {
      ShimmerEffect()
      false
    }

    else -> {
      true
    }
  }
}

@Composable
private fun ShimmerEffect() {
  Column {
    repeat(8) {
      OrderCardShimmerEffect()
    }
  }
}

@Composable
fun OrderCardShimmerEffect(
  modifier: Modifier = Modifier
) {
  TripTrackTheme() {
    Row(
      modifier = modifier
    ) {
      Box(
        modifier = Modifier
          .height(CardHeight)
          .clip(RoundedCornerShape(20.dp))
          .padding(all = 8.dp)
      ) {
        Row {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
              .fillMaxHeight()
              .width(50.dp)
          ) {
            Box(
              Modifier
                .loadShimmerEffect()
                .size(50.dp)
            ) {} // yes
          }
          Column(
            modifier = Modifier
              .fillMaxSize(1f)
              .padding(top = 10.dp, start = 6.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
          ) {
            Row(
              Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .loadShimmerEffect()
            ) {
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Row(
              Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .loadShimmerEffect()
            ) {
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Row(
              Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .loadShimmerEffect()
            ) {
            }
          }
        }
      }
    }
  }
}


fun Modifier.loadShimmerEffect(): Modifier = composed {
  val infiniteTransition = rememberInfiniteTransition()
  val alphaValue by infiniteTransition.animateFloat(
    initialValue = 0.2f,
    targetValue = 0.7f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1000, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    )
  )
  background(color = colorResource(id = R.color.shimmer).copy(alpha = alphaValue))
}

//todo empty screen


