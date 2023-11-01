package com.example.triptrack.screen.statistics

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun StatisticsScreen(navController: NavHostController) {
  val pointsData: List<Point> =
    listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))

  val xAxisData = AxisData.Builder()
    .axisStepSize(50.dp)
    .backgroundColor(Color.Blue)
    .steps(pointsData.size - 1)
    .labelData { i -> (i + 1).toString() }
    .labelAndAxisLinePadding(30.dp)
    .build()

  val steps = 20
  val yScale = 100 / steps
  val yAxisData = AxisData.Builder()
    .steps(steps)
    .backgroundColor(Color.Red)
    .labelAndAxisLinePadding(20.dp)
    .labelData { i ->
      (i * yScale).toString()
    }.build()

  val lineChartData = LineChartData(
    linePlotData = LinePlotData(
      lines = listOf(
        Line(
          dataPoints = pointsData,
          lineStyle = LineStyle(color = Color.Green, width = 5f),
          intersectionPoint = IntersectionPoint(Color.Blue, radius = 3.dp),
          selectionHighlightPoint = SelectionHighlightPoint(),
          shadowUnderLine = ShadowUnderLine(),
          selectionHighlightPopUp = SelectionHighlightPopUp()
        )
      ),
    ),
    xAxisData = xAxisData,
    yAxisData = yAxisData,
    gridLines = GridLines(),
    backgroundColor = Color.White
  )


  val pieChartData = PieChartData(
    slices = listOf(
      PieChartData.Slice("SciFi", 65f, Color.LightGray),
      PieChartData.Slice("Comedy", 35f, Color.Blue),
      PieChartData.Slice("Drama", 10f, Color.Gray),
      PieChartData.Slice("Romance", 40f, Color.Magenta)
    ),
    plotType = PlotType.Pie
  )

  val pieChartConfig = PieChartConfig(
    isAnimationEnable = true,
    showSliceLabels = true,
    animationDuration = 1500,
    sliceLabelTextColor = Color.Black,
    sliceLabelTypeface = Typeface.DEFAULT,
    labelFontSize = 14.sp,
  )


  val donutChartData = PieChartData(
    slices = listOf(
      PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
      PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
      PieChartData.Slice("Lenovo", 40f, Color(0xFFEC9F05)),
      PieChartData.Slice("Asus", 10f, Color(0xFFF53844))
    ),
    plotType = PlotType.Donut
  )


  val donutChartConfig = PieChartConfig(
    isAnimationEnable = true,
    showSliceLabels = true,
    animationDuration = 1500,
    sliceLabelTextColor = Color.Black,
    sliceLabelTypeface = Typeface.DEFAULT,
    labelFontSize = 14.sp,
  )


  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Yellow),
    contentAlignment = Alignment.Center
  ) {
    DonutPieChart(
      modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
      donutChartData,
      donutChartConfig
    )
  }
}

@Composable
@Preview(showBackground = true)
fun StatPreview() {
  val navController = rememberNavController()
  StatisticsScreen(navController = navController)
}