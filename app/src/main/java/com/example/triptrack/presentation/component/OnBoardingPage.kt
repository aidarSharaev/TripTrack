package com.example.triptrack.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triptrack.presentation.onboarding.OnBoardingEvent
import com.example.triptrack.presentation.onboarding.Page
import com.example.triptrack.presentation.common.TopComponent
import kotlinx.coroutines.launch


@Composable
fun OnBoardingPage(
  modifier: Modifier = Modifier,
  page: Page
) {
  Column(
    modifier = Modifier.fillMaxSize(),
  ) {
    TopComponent(page)
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomButton(
  buttonValue: List<String>,
  pagerState: PagerState,
  pageSize: Int,
  event: (OnBoardingEvent) -> Unit
) {
  val scope = rememberCoroutineScope()
  if(buttonValue[0].isNotEmpty()) {
    Button(
      onClick = {
        scope.launch {
          pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
        }
      },
      shape = MaterialTheme.shapes.medium
    ) {
      Text(
        text = buttonValue[0], fontSize = 12.sp
      )
    }
  }
  Spacer(modifier = Modifier.width(10.dp))
  Button(
    onClick = {
      scope.launch {
        if(pagerState.currentPage != pageSize - 1)
          pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
        else
          event(OnBoardingEvent.SaveAppEntry)
      }
    },
    modifier = Modifier.padding(end = 15.dp),
    shape = RoundedCornerShape(size = 5.dp),
  ) {
    Text(
      text = buttonValue[1], fontSize = 12.sp
    )
  }
}

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

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PagePreview() {
  //OnBoardingScreen(page = pagesList[1])
}

//navigationBarsPadding(),