package com.example.triptrack.presentation.onboarding.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triptrack.presentation.onboarding.Page
import com.example.triptrack.presentation.onboarding.pagesList

@Composable
fun OnBoardingPage(
  page: Page
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.primary)
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
    ) {
      Image(
        painter = painterResource(id = page.image),
        contentDescription = "page image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight(0.6f)
      )
      Spacer(
        modifier = Modifier.height(20.dp)
      )
      Text(
        text = page.title,
        modifier = Modifier.padding(),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
      )
      Spacer(
        modifier = Modifier.height(15.dp)
      )
      Text(
        text = page.text,
        modifier = Modifier.padding(),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
      )
      Row(
        modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.error),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Box(
          modifier = Modifier
            .background(Color.Red)
            .padding(start = 15.dp, bottom = 15.dp),
          contentAlignment = Alignment.BottomStart
        ) {
          Row() {
            Indicator()
            Spacer(modifier = Modifier.width(10.dp))
            Indicator()
            Spacer(modifier = Modifier.width(10.dp))
            Indicator()
          }
        }
        Box(
          modifier = Modifier.background(Color.Green),
          contentAlignment = Alignment.BottomEnd
        ) {
          Row(
            modifier = Modifier.padding(
              bottom = 15.dp,
            )
          ) {
            Button(
              onClick = {},
              shape = MaterialTheme.shapes.medium
            ) {
              Text(
                "Назад",
                fontSize = 12.sp
              )
            }
            Spacer(
              modifier = Modifier.width(10.dp)
            )
            Button(
              onClick = {},
              modifier = Modifier.padding(
                end = 15.dp
              ),
              shape = MaterialTheme.shapes.medium
            ) {
              Text("Начнем",
                fontSize = 12.sp
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun Indicator() {
  Canvas(modifier = Modifier, onDraw = {
    drawCircle(
      Color.Blue, 10f,
    )
  })
}

@Preview(showBackground = true)
@Composable
fun PagePreview() {
  OnBoardingPage(pagesList[0])
  //Indicator()
}

/*
*
//          }*/