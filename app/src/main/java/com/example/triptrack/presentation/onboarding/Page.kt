package com.example.triptrack.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.triptrack.R

data class Page(
  val title: String,
  val text: String,
  @DrawableRes val image: Int
)

val pagesList = listOf<Page>(
  Page(
    title = "\tДобро пожаловать в приложение",
    text = "На главном экране вы можете внести поездки",
    image = R.drawable.onboarding3
  ),
  Page(
    title = "Добро пожаловать в приложение",
    text = "На главном экране вы можете внести поездки",
    image = R.drawable.onboarding3
  ),
  Page(
    title = "Добро пожаловать в приложение",
    text = "На главном экране вы можете внести поездки",
    image = R.drawable.onboarding3
  ),
)