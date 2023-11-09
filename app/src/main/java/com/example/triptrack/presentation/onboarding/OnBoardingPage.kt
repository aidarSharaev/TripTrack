package com.example.triptrack.presentation.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triptrack.presentation.component.TopComponent
import kotlinx.coroutines.launch

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
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
) {
    val scope = rememberCoroutineScope()
    if (buttonValue[0].isNotEmpty()) {
        Button(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                }
            },
            shape = MaterialTheme.shapes.medium,
        ) {
            Text(
                text = buttonValue[0],
                fontSize = 12.sp,
            )
        }
    }
    Spacer(modifier = Modifier.width(10.dp))
    if (buttonValue[1].isNotEmpty()) {
        Button(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                }
            },
            modifier = Modifier.padding(end = 15.dp),
            shape = RoundedCornerShape(size = 5.dp),
        ) {
            Text(
                text = buttonValue[1],
                fontSize = 12.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PagePreview() {
    OnBoardingPage(page = pagesList[3])
}

// navigationBarsPadding(),
