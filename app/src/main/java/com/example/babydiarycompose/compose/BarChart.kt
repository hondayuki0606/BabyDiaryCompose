package com.example.babydiarycompose.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.data.BarChartAttributes
import com.example.babydiarycompose.data.Datum
import com.example.babydiarycompose.data.YAxisAttributes
import com.example.babydiarycompose.data.makeYAxisAttributes
import java.lang.Float.min
import kotlin.math.abs

@Composable
fun <T> BarChart(
    data: List<Datum<T>>,
    modifier: Modifier = Modifier,
    attributes: BarChartAttributes<T> = BarChartAttributes()
) where T : Number, T : Comparable<T> {
    BoxWithConstraints(modifier = modifier) {
        // x,y軸の描画エリアを設定する
        val width = with(LocalDensity.current) { maxWidth.toPx() }
        val height = with(LocalDensity.current) { maxHeight.toPx() }
        val axisArea = Rect(left = 0f, top = 0f, right = width, bottom = height)

        // データの描画エリアを設定する
        val plotArea = Rect(
            left = axisArea.left, top = axisArea.top, right = axisArea.right,
            bottom = axisArea.bottom
        )

        // y軸の範囲を求める
        val yAxisAttributes = makeYAxisAttributes(
            data = data, attributes = attributes)
        // 省略 ...
        Canvas(Modifier.fillMaxSize()) {
            // 省略 ...
            // データを描画する
            drawData(
                data = data,
                yAxisAttributes = yAxisAttributes,
                area = plotArea,
                attributes = attributes
            )
        }
    }
}

private fun <T> DrawScope.drawData(
    data: List<Datum<T>>,
    yAxisAttributes: YAxisAttributes,
    area: Rect,
    attributes: BarChartAttributes<T>
) where T : Number, T : Comparable<T> {
    val yMin = yAxisAttributes.minValue
    val yMax = yAxisAttributes.maxValue

    // データ値を座標に変換する係数を計算 y = ax + b
    val yRange = yMax - yMin
    val a = (area.top - area.bottom) / yRange
    val b = (area.bottom * yMax - area.top * yMin) / yRange
    val barInterval = attributes.barInterval.toPx()
    val barWidth = attributes.barWidth.toPx()
    val xStart = area.left + (barInterval - barWidth) / 2f
    clipRect(
        left = area.left, top = area.top, right = area.right, bottom = area.bottom) {
        data.forEachIndexed { index, datum ->
            // 描画するbarの座標を計算
            val t = a * datum.value.toFloat()
            val y = t + b
            val top = min(y, b)
            val barHeight = abs(t)
            val left = xStart + barInterval * index
            drawRect(
                color = attributes.barColor,
                topLeft = Offset(x = left, y = top),
                size = Size(width = barWidth, height = barHeight)
            )
        }
    }
}

@Preview(widthDp = 400, heightDp = 400)
@Composable
private fun BarChartPreview2() {
    BarChart(
        data = listOf(
            Datum(1, "d1"),
            Datum(2, "d2"),
            Datum(3, "d3"),
            Datum(4, "d4"),
            Datum(5, "d5"),
            Datum(6, "d6")
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}
