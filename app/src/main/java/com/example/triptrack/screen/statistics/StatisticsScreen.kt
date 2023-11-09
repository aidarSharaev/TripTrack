package com.example.triptrack.screen.statistics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.triptrack.model.OrderByMonth
import com.example.triptrack.model.OrderPerMonth
import com.example.triptrack.presentation.navgraph.Dimens.navBarPadding
import com.example.triptrack.ui.theme.fontItalic
import com.example.triptrack.utils.Constants.MONTHS
import java.lang.Float.max
import java.lang.Float.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatisticsScreen(navController: NavHostController) {
    val orderPer = listOf<OrderPerMonth>(
        OrderPerMonth(id = 0, month = "МЕС", count = 0),
        OrderPerMonth(id = 1, month = "Январь", count = 12),
        OrderPerMonth(id = 2, month = "Февраль", count = 15),
        OrderPerMonth(id = 3, month = "Март", count = 27),
        OrderPerMonth(id = 4, month = "Апрель", count = 9),
        OrderPerMonth(id = 5, month = "Май", count = 20),
    )
    val orderBy = listOf<OrderByMonth>(
        OrderByMonth(id = 1, month = 7, count = 55),
        OrderByMonth(id = 2, month = 8, count = 67),
        OrderByMonth(id = 3, month = 9, count = 67),
        OrderByMonth(id = 4, month = 10, count = 80),
        OrderByMonth(id = 5, month = 11, count = 89),
    )

    val pagerState = rememberPagerState(
        pageCount = { 2 },
    )
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
    ) { page ->
        when (page) {
            0 -> {
                // StatOrderPage()
                StatOrderPage(orderBy = orderBy, orderPer = orderPer)
            }

            1 -> {
                // StatEmployerPage()
                Donut()
            }

            else -> {
                // StatEmployerPage()
                StatOrderPage(orderBy = orderBy, orderPer = orderPer)
            }
        }
    }
}

fun copyOrderBy(
    listBy: MutableList<Float>,
    orderBy: List<OrderByMonth>,
    pointsDataByMonth: MutableList<Point>,
) {
    repeat(orderBy.size) { index ->
        pointsDataByMonth.add(
            Point(
                orderBy[index].month.toFloat(),
                orderBy[index].count.toFloat(),
            ),
        )
        listBy.add(orderBy[index].count.toFloat())
    }
}

fun copyOrderPer(
    listPer: MutableList<String>,
    orderPer: List<OrderPerMonth>,
    pointsDataPerMonth: MutableList<Point>,
) {
    repeat(orderPer.size) { index ->
        pointsDataPerMonth.add(
            Point(
                index.toFloat(),
                orderPer[index].count.toFloat(),
            ),
        )
        listPer.add(orderPer[index].month.substring(0, 3))
    }
}

@Composable
fun StatOrderPage(
    // order: Flow<List<OrderByMonth>>,
    orderBy: List<OrderByMonth>,
    orderPer: List<OrderPerMonth>,
) {
    val pointsDataPerMonth = mutableListOf<Point>()
    val pointsDataByMonth = mutableListOf<Point>()

    val listPer = mutableListOf<String>()
    val listBy = mutableListOf<Float>()

    copyOrderBy(
        listBy = listBy,
        orderBy = orderBy,
        pointsDataByMonth = pointsDataByMonth,
    )

    copyOrderPer(
        listPer = listPer,
        orderPer = orderPer,
        pointsDataPerMonth = pointsDataPerMonth,
    )

    var maxBy = -1f
    var minBy = 10000f
    val c = pointsDataByMonth.last().x
    pointsDataByMonth.onEach {
        maxBy = max(it.y, maxBy)
        minBy = min(it.y, minBy)
    }

    val steps = 5
    val yScale = (((maxBy - minBy) / steps) + 1).toInt()

    val xAxisDataBy = AxisData.Builder().axisStepSize(48.dp).backgroundColor(Color.Transparent)
        .steps(pointsDataByMonth.size)
        .labelData { i ->
            if (i < pointsDataByMonth.size) {
                pointsDataByMonth[i].x.formatToSinglePrecision()
            } else {
                (pointsDataByMonth.last().x + 1).formatToSinglePrecision()
            }
        }
        .labelAndAxisLinePadding(15.dp).build()

    val yAxisDataBy =
        AxisData.Builder().steps(steps).axisStepSize(75.dp).backgroundColor(Color.Transparent)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i -> (i * yScale + minBy).formatToSinglePrecision() }.build()

    val xAxisDataPer =
        AxisData.Builder()
            .axisStepSize(48.dp)
            .backgroundColor(Color.Transparent)
            .steps(pointsDataPerMonth.size).labelData { i ->
                if (i < listPer.size) {
                    listPer[i]
                } else {
                    val it = MONTHS.indexOf(listPer.last()) + 1
                    if (it == MONTHS.size - 1) MONTHS[it] else MONTHS[0]
                }
            }
            .labelAndAxisLinePadding(15.dp).build()

    val yAxisDataPer =
        AxisData.Builder()
            .steps(steps - 1)
            .axisStepSize(75.dp)
            .backgroundColor(Color.Transparent)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i -> pointsDataByMonth[i].y.formatToSinglePrecision() }
            .build()

    val lineChartDataBy = lineChartBuilder(
        xAxisData = xAxisDataBy,
        yAxisData = yAxisDataBy,
        dataPoints = pointsDataByMonth,
    )

    val lineChartDataPer = lineChartBuilder(
        xAxisData = xAxisDataPer,
        yAxisData = yAxisDataPer,
        dataPoints = pointsDataPerMonth,
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = navBarPadding,
        ),
    ) {
        item {
            TextForStat(
                text = "Заказы за все время по месяцам",
            )
        }
        item {
            SurfaceBoxWithElevation(lineChartData = lineChartDataBy)
        }
        item {
            TextForStat(
                text = "Заказы по месяцам",
            )
        }
        item {
            SurfaceBoxWithElevation(lineChartData = lineChartDataPer)
        }
    }
}

// @Composable
// fun StatEmployerPage(
//    // order: Flow<List<OrderByMonth>>,
//    orderBy: List<OrderByMonth>,
//    orderPer: List<OrderPerMonth>,
// ) {
//    val pointsDataPerMonth = mutableListOf<Point>()
//    val pointsDataByMonth = mutableListOf<Point>()
//
//    val listPer = mutableListOf<String>()
//    val listBy = mutableListOf<Float>()
//
//    copyOrderBy(
//        listBy = listBy,
//        orderBy = orderBy,
//        pointsDataByMonth = pointsDataByMonth,
//    )
//
//    copyOrderPer(
//        listPer = listPer,
//        orderPer = orderPer,
//        pointsDataPerMonth = pointsDataPerMonth,
//    )
//
//    var maxBy = -1f
//    var minBy = 10000f
//    val c = pointsDataByMonth.last().x
//    pointsDataByMonth.onEach {
//        maxBy = max(it.y, maxBy)
//        minBy = min(it.y, minBy)
//    }
//
//    val steps = 5
//    val yScale = (((maxBy - minBy) / steps) + 1).toInt()
//
//    val xAxisDataBy = AxisData.Builder().axisStepSize(48.dp).backgroundColor(Color.Transparent)
//        .steps(pointsDataByMonth.size)
//        .labelData { i ->
//            if (i < pointsDataByMonth.size) {
//                pointsDataByMonth[i].x.formatToSinglePrecision()
//            } else {
//                (pointsDataByMonth.last().x + 1).formatToSinglePrecision()
//            }
//        }
//        .labelAndAxisLinePadding(15.dp).build()
//
//    val yAxisDataBy =
//        AxisData.Builder().steps(steps).axisStepSize(75.dp).backgroundColor(Color.Transparent)
//            .labelAndAxisLinePadding(20.dp)
//            .labelData { i -> (i * yScale + minBy).formatToSinglePrecision() }.build()
//
//    val xAxisDataPer =
//        AxisData.Builder()
//            .axisStepSize(48.dp)
//            .backgroundColor(Color.Transparent)
//            .steps(pointsDataPerMonth.size).labelData { i ->
//                if (i < listPer.size) {
//                    listPer[i]
//                } else {
//                    val it = MONTHS.indexOf(listPer.last()) + 1
//                    if (it == MONTHS.size - 1) MONTHS[it] else MONTHS[0]
//                }
//            }
//            .labelAndAxisLinePadding(15.dp).build()
//
//    val yAxisDataPer =
//        AxisData.Builder()
//            .steps(steps - 1)
//            .axisStepSize(75.dp)
//            .backgroundColor(Color.Transparent)
//            .labelAndAxisLinePadding(20.dp)
//            .labelData { i -> pointsDataByMonth[i].y.formatToSinglePrecision() }
//            .build()
//
//    val lineChartDataBy = lineChartBuilder(
//        xAxisData = xAxisDataBy,
//        yAxisData = yAxisDataBy,
//        dataPoints = pointsDataByMonth,
//    )
//
//    val lineChartDataPer = lineChartBuilder(
//        xAxisData = xAxisDataPer,
//        yAxisData = yAxisDataPer,
//        dataPoints = pointsDataPerMonth,
//    )
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize(),
//
//    ) {
//        item {
//            TextForStat(
//                text = "Заказы за все время по месяцам",
//            )
//        }
//        item {
//            SurfaceBoxWithElevation(lineChartData = lineChartDataBy)
//        }
//        item {
//            TextForStat(
//                text = "Заказы по месяцам",
//            )
//        }
//        item {
//            SurfaceBoxWithElevation(lineChartData = lineChartDataPer)
//        }
//    }
// }

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
