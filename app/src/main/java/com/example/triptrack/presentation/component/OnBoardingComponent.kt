package com.example.triptrack.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triptrack.presentation.onboarding.BottomButton
import com.example.triptrack.presentation.onboarding.Page

@Composable
fun TopComponent(
  page: Page
) {
  Image(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(0.6f),
    painter = painterResource(id = page.image),
    contentDescription = "page image",
    contentScale = ContentScale.Crop,
  )
  Spacer(
    modifier = Modifier.height(20.dp)
  )
  Text(
    text = page.title,
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 30.sp
  )
  Text(
    text = page.text,
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp
  )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomComponent(
  pageSize: Int,
  pagerState: PagerState,
  buttonValue: List<String>,
) {
  Row(
    modifier = Modifier
      .fillMaxSize(),
    verticalAlignment = Alignment.Bottom,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Box(
      modifier = Modifier
        .padding(start = 15.dp, bottom = 15.dp),
      contentAlignment = Alignment.BottomStart
    ) {
      Indicator(pageSize = pageSize, selectedPage = pagerState.currentPage)
    }
    Box(
      contentAlignment = Alignment.BottomEnd
    ) {
      Row(
        modifier = Modifier.padding(bottom = 15.dp)
      ) {
        BottomButton(
          buttonValue = buttonValue,
          pagerState = pagerState,
        )
      }
    }
  }
}