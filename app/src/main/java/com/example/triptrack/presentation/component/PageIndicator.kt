package com.example.triptrack.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Indicator(
  pageSize: Int,
  selectedPage: Int
) {
  Row(modifier = Modifier) {
    repeat(pageSize) { index ->
      Box(
        modifier = Modifier
          .size(10.dp)
          .clip(CircleShape)
          .background(
            color = if(index == selectedPage) Color.White else Color.Black
          )
      ) {}
      Spacer(modifier = Modifier.width(2.dp))
    }
  }
}