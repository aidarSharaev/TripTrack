package com.example.triptrack.screen.statistics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
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
import com.example.triptrack.R
import com.example.triptrack.presentation.navgraph.Dimens.navBarPadding
import com.example.triptrack.ui.theme.fontItalic

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel,
    navController: NavHostController,
) {
    val uiState = viewModel.uiState.collectAsState()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        TextField(
            value = "uiState.value.currentMonth",
            onValueChange = {

            },
        )
        Button(
            modifier = Modifier.padding(top = 150.dp),
            onClick = { viewModel.put() },
        ) {}
    }
//    val uiState = viewModel.uiState.collectAsState()
//
//    val pagerState = rememberPagerState(
//        pageCount = { 2 },
//    )
//    HorizontalPager(
//        state = pagerState,
//        modifier = Modifier
//            .statusBarsPadding()
//            .fillMaxSize(),
//    ) { page ->
//        when (page) {
//            0 -> {
//                StatOrderPage(
//                    monthlyOrdersLabel = uiState.value.monthlyOrdersLabel,
//                    ordersPerMonthLabel = uiState.value.ordersPerMonthLabel,
//                    pointsDataMonthly = uiState.value.pointsDataMonthly,
//                    pointsDataPerMonth = uiState.value.pointsDataPerMonth,
//                    maxBy = viewModel.maxBy,
//                    minBy = viewModel.minBy,
//                )
//            }
//
//            1 -> {
//                Donut()
//            }
//
//            else -> {
//                StatOrderPage(
//                    monthlyOrdersLabel = uiState.value.monthlyOrdersLabel,
//                    ordersPerMonthLabel = uiState.value.ordersPerMonthLabel,
//                    pointsDataMonthly = uiState.value.pointsDataMonthly,
//                    pointsDataPerMonth = uiState.value.pointsDataPerMonth,
//                    maxBy = viewModel.maxBy,
//                    minBy = viewModel.minBy,
//                )
//            }
//        }
//    }
}

@Composable
fun StatOrderPage(
    monthlyOrdersLabel: MutableList<Float>,
    pointsDataMonthly: MutableList<Point>,
    pointsDataPerMonth: MutableList<Point>,
    ordersPerMonthLabel: MutableList<String>,
    maxBy: Float,
    minBy: Float,
) {
    val steps = 5
    val yScale = (((maxBy - minBy) / steps) + 1).toInt()

    val xAxisDataMonthly = AxisData.Builder().axisStepSize(48.dp).backgroundColor(Color.Transparent)
        .steps(pointsDataMonthly.size)
        .labelData { i ->
            if (i < pointsDataMonthly.size) {
                pointsDataMonthly[i].x.formatToSinglePrecision()
            } else {
                (pointsDataMonthly.last().x + 1).formatToSinglePrecision()
            }
        }
        .labelAndAxisLinePadding(15.dp).build()

    val yAxisDataMonthly =
        AxisData.Builder().steps(steps).axisStepSize(75.dp).backgroundColor(Color.Transparent)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i -> (i * yScale + minBy).toString() }.build()

    val xAxisDataPerMonth =
        AxisData.Builder()
            .axisStepSize(48.dp)
            .backgroundColor(Color.Transparent)
            .steps(pointsDataPerMonth.size).labelData { i ->
                "A"
//                if (i < ordersPerMonthLabel.size) {
//                    ordersPerMonthLabel[i]
//                }
// i                else {
//                    val it = MONTHS.indexOf(ordersPerMonthLabel.last()) + 1
//                    if (it == MONTHS.size - 1) MONTHS[it] else MONTHS[0]
//                }
            }
            .labelAndAxisLinePadding(15.dp).build()

    val yAxisDataPerMonth =
        AxisData.Builder()
            .steps(steps - 1)
            .axisStepSize(75.dp)
            .backgroundColor(Color.Transparent)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i -> pointsDataPerMonth[i].y.formatToSinglePrecision() }
            .build()

    val lineChartDataBy = lineChartBuilder(
        xAxisData = xAxisDataMonthly,
        yAxisData = yAxisDataMonthly,
        dataPoints = pointsDataMonthly,
    )

    val lineChartDataPer = lineChartBuilder(
        xAxisData = xAxisDataPerMonth,
        yAxisData = yAxisDataPerMonth,
        dataPoints = pointsDataPerMonth,
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = navBarPadding,
        ),
    ) {
        item {
            TextForStat(text = stringResource(R.string.monthly_orders))
        }
        item {
            SurfaceBoxWithElevation(lineChartData = lineChartDataBy)
        }
        item {
            TextForStat(
                text = stringResource(R.string.orders_per_month),
            )
        }
        item {
            SurfaceBoxWithElevation(lineChartData = lineChartDataPer)
        }
    }
}

@Composable
fun SurfaceBoxWithElevation(
    lineChartData: LineChartData,
) {
    Surface(
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth(),
        shadowElevation = 5.dp,
    ) {
        Box {
            LineChart(
                modifier = Modifier
                    .fillMaxSize(),
                lineChartData = lineChartData,
            )
        }
    }
}

@Composable
fun TextForStat(
    text: String,
) {
    Text(
        modifier = Modifier.height(30.dp),
        text = text,
        fontFamily = fontItalic,
        fontSize = 18.sp,
    )
}

fun lineChartBuilder(
    xAxisData: AxisData,
    yAxisData: AxisData,
    dataPoints: List<Point>,
): LineChartData {
    return LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = dataPoints,
                    lineStyle = LineStyle(color = Color.Green, width = 5f),
                    intersectionPoint = IntersectionPoint(Color.Blue, radius = 3.dp),
                    selectionHighlightPoint = SelectionHighlightPoint(),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPopUp = SelectionHighlightPopUp(),
                ),
            ),
        ),

        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White,
    )
}

@Composable
@Preview(showBackground = true)
fun StatPreview() {
//    val ordersPer = listOf<OrderPerMonth>(
//        OrderPerMonth(id = 0, month = "МЕС", count = 0),
//        OrderPerMonth(id = 1, month = "Январь", count = 12),
//        OrderPerMonth(id = 2, month = "Февраль", count = 15),
//        OrderPerMonth(id = 3, month = "Март", count = 27),
//        OrderPerMonth(id = 4, month = "Апрель", count = 9),
//        OrderPerMonth(id = 5, month = "Май", count = 20),
//    )
//    val orderBY = listOf<OrderByMonth>(
//        OrderByMonth(id = 1, month = 7, count = 55),
//        OrderByMonth(id = 2, month = 8, count = 67),
//        OrderByMonth(id = 3, month = 9, count = 67),
//        OrderByMonth(id = 4, month = 10, count = 80),
//        OrderByMonth(id = 5, month = 11, count = 89),
//    )
//    StatOrderPage(orderBy = orderBY, orderPer = ordersPer)

    Donut()
}

@Composable
fun Donut() {
    val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
            PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
            PieChartData.Slice("Lenovo", 40f, Color(0xFFEC9F05)),
            PieChartData.Slice("Asus", 10f, Color(0xFFF53844)),
        ),
        plotType = PlotType.Donut,
    )

    val donutChartConfig = PieChartConfig(
        strokeWidth = 120f,
        activeSliceAlpha = .9f,
        inActiveSliceAlpha = 0.5f,
        isAnimationEnable = true,
        showSliceLabels = true,
        labelType = PieChartConfig.LabelType.VALUE,
        labelColor = Color.Black,
        labelColorType = PieChartConfig.LabelColorType.SPECIFIED_COLOR,
        isClickOnSliceEnabled = true,
        labelFontSize = 14.sp,
        labelVisible = true,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center,
    ) {
        DonutPieChart(
            modifier = Modifier
                .padding(50.dp)
                .width(300.dp)
                .height(300.dp),
            pieChartData = donutChartData,
            pieChartConfig = donutChartConfig,
        )
    }
}
