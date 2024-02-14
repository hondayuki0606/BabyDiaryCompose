package com.example.babydiarycompose.compose

import android.graphics.RectF
import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.data.BarChartAttributes
import com.example.babydiarycompose.data.Datum
import com.example.babydiarycompose.data.YAxisAttributes
import com.example.babydiarycompose.data.makeYAxisAttributes
import com.example.babydiarycompose.utils.Pink
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


// grid値の文字列とy軸の間のpadding
private val PADDING = 8.dp

@Composable
fun <T> BarChart(
    data: List<Datum<T>>,
    modifier: Modifier = Modifier,
    attributes: BarChartAttributes<T> = BarChartAttributes()
) where T : Number, T : Comparable<T> {
    BoxWithConstraints(modifier = modifier) {
        // 文字列のサイズの計測器を準備する
        val textMeasurer = rememberTextMeasurer()

        // データラベルを計測する
        val dataLabelLayoutResults =
            measureDataLabel(data = data, attributes = attributes, textMeasurer = textMeasurer)
        // データラベルの最大の高さを取得する
        val maxDataLabelHeight = dataLabelLayoutResults.maxOfOrNull {
            it.size.height
        } ?: 0

        // y軸の範囲とgridを求める
        val yAxisAttributes = makeYAxisAttributes(data = data, attributes = attributes)

        // grid値を表す文字列を計測する
        val gridValueLayoutResults = measureGridValue(
            yAxisAttributes = yAxisAttributes,
            attributes = attributes,
            textMeasurer = textMeasurer
        )
        // Grid値の最大の幅を取得する
        val maxGridValueWidth = gridValueLayoutResults.maxOfOrNull {
            it.size.width
        } ?: 0

        // データ値を表す文字列を計測する
        val dataValueLayoutResults =
            measureDataValue(data = data, attributes = attributes, textMeasurer = textMeasurer)
        // データ値を表す文字列の最大の高さを取得する
        val maxDataValueHeight = dataValueLayoutResults.maxOfOrNull {
            it.size.height
        } ?: 0

        // x,y軸の描画エリアを設定する
        val width = with(LocalDensity.current) { maxWidth.toPx() }
        val height = with(LocalDensity.current) { maxHeight.toPx() }
        val padding = with(LocalDensity.current) { PADDING.toPx() }
        val axisArea = Rect(
            left = maxGridValueWidth.toFloat() + padding, // grid値表示分、軸の位置をずらす
            top = 0f,
            right = width,
            bottom = height - maxDataLabelHeight // データラベル分、軸の位置をずらす
        )

        // データの描画エリアを設定する
        val posDataValueSpace =
            data.find { 0f <= it.value[0].toFloat() }?.run { maxDataValueHeight } ?: 0
        val negDataValueSpace =
            data.find { it.value[0].toFloat() < 0f }?.run { maxDataValueHeight } ?: 0
        val plotArea = Rect(
            left = axisArea.left,
            top = axisArea.top + posDataValueSpace,
            right = axisArea.right,
            bottom = axisArea.bottom - negDataValueSpace
        )

        // データラベルの描画エリアを設定する
        val dataLabelArea = Rect(
            left = axisArea.left,
            top = axisArea.bottom,
            right = axisArea.right,
            bottom = axisArea.bottom + maxDataLabelHeight
        )

        // データ値の描画エリアを設定する
        val dataValueArea = Rect(
            left = axisArea.left,
            top = axisArea.top,
            right = axisArea.right,
            bottom = axisArea.bottom
        )

        // グリッド線の描画エリアを設定する
        val gridLineArea = Rect(
            left = plotArea.left,
            top = plotArea.top,
            right = plotArea.right,
            bottom = plotArea.bottom
        )

        // グリッド値の描画エリアを設定する
        val gridValueArea = Rect(
            left = 0f,
            top = gridLineArea.top,
            right = gridLineArea.left - padding,
            bottom = gridLineArea.bottom
        )

        Canvas(Modifier.fillMaxSize()) {
            // x,y軸を描画する
            drawAxis(area = axisArea, attributes = attributes)

            // データを描画する
            drawData(
                data = data,
                dataLabelLayoutResults = dataLabelLayoutResults,
                dataValueLayoutResults = dataValueLayoutResults,
                yAxisAttributes = yAxisAttributes,
                plotArea = plotArea,
                dataLabelArea = dataLabelArea,
                dataValueArea = dataValueArea,
                attributes = attributes
            )

            // grid値とgrid線を描画する
            drawGrid(
                yAxisAttributes = yAxisAttributes,
                gridValueLayoutResults = gridValueLayoutResults,
                gridLineArea = gridLineArea,
                gridValueArea = gridValueArea,
                attributes = attributes
            )
        }
    }
}

// データラベルを計測する
private fun <T> measureDataLabel(
    data: List<Datum<T>>,
    attributes: BarChartAttributes<T>,
    textMeasurer: TextMeasurer
): List<TextLayoutResult> where T : Number, T : Comparable<T> {
    val textLayoutResults = mutableListOf<TextLayoutResult>()
    data.forEach {
        val label = it.label
        textLayoutResults.add(
            textMeasurer.measure(
                text = AnnotatedString(label),
                style = TextStyle(
                    color = attributes.dataLabelTextColor,
                    fontSize = attributes.dataLabelTextSize
                )
            )
        )
    }
    return textLayoutResults
}

// grid値を表す文字列を計測する
private fun <T> measureGridValue(
    yAxisAttributes: YAxisAttributes,
    attributes: BarChartAttributes<T>,
    textMeasurer: TextMeasurer
): List<TextLayoutResult> where T : Number, T : Comparable<T> {
    val textLayoutResults = mutableListOf<TextLayoutResult>()
    val formatter = attributes.gridValueFormatPattern?.let {
        DecimalFormat(it)
    } ?: NumberFormat.getInstance()
    yAxisAttributes.gridList.forEach {
        val label = formatter.format(it)
        textLayoutResults.add(
            textMeasurer.measure(
                text = AnnotatedString(label),
                style = TextStyle(
                    color = attributes.gridValueTextColor,
                    fontSize = attributes.gridValueTextSize
                )
            )
        )
    }
    return textLayoutResults
}

// データ値を表す文字列を計測する
private fun <T> measureDataValue(
    data: List<Datum<T>>,
    attributes: BarChartAttributes<T>,
    textMeasurer: TextMeasurer
): List<TextLayoutResult> where T : Number, T : Comparable<T> {
    val textLayoutResults = mutableListOf<TextLayoutResult>()
    val formatter = attributes.dataValueFormatPattern?.let {
        DecimalFormat(it)
    } ?: NumberFormat.getInstance()
    data.forEach {
        val value = formatter.format(it.value[0])
        textLayoutResults.add(
            textMeasurer.measure(
                text = AnnotatedString(value),
                style = TextStyle(
                    color = attributes.dataLabelTextColor,
                    fontSize = attributes.dataLabelTextSize
                )
            )
        )
    }
    return textLayoutResults
}

// x,y軸を描画する
private fun <T> DrawScope.drawAxis(
    area: Rect,
    attributes: BarChartAttributes<T>
) where T : Number, T : Comparable<T> {
    drawLine(
        color = attributes.axisLineColor,
        start = Offset(area.left, area.top),
        end = Offset(area.left, area.bottom)
    )
    drawLine(
        color = attributes.axisLineColor,
        start = Offset(area.left, area.bottom),
        end = Offset(area.right, area.bottom)
    )
}

private fun Rect.union(other: Rect): Rect {
    return Rect(
        min(left, other.left),
        min(top, other.top),
        max(right, other.right),
        max(bottom, other.bottom)
    )
}

// データを描画する
private fun <T> DrawScope.drawData(
    data: List<Datum<T>>,
    dataLabelLayoutResults: List<TextLayoutResult>,
    dataValueLayoutResults: List<TextLayoutResult>,
    yAxisAttributes: YAxisAttributes,
    plotArea: Rect,
    dataLabelArea: Rect,
    dataValueArea: Rect,
    attributes: BarChartAttributes<T>
) where T : Number, T : Comparable<T> {
    val yMin = yAxisAttributes.minValue
    val yMax = yAxisAttributes.maxValue

    // データ値を座標に変換する係数を計算 y = ax + b
    val yRange = yMax - yMin
    val a = (plotArea.top - plotArea.bottom) / yRange
    val b = (plotArea.bottom * yMax - plotArea.top * yMin) / yRange
    val barInterval = attributes.barInterval.toPx()
    val barWidth = attributes.barWidth.toPx()
    val xStart = plotArea.left + (barInterval - barWidth) / 2f
    // clipの範囲をプロット領域とラベル領域、データ値領域の全てを含むようにする
    val unionArea = plotArea.union(dataLabelArea).union(dataValueArea)
    clipRect(
        left = unionArea.left,
        top = unionArea.top,
        right = unionArea.right,
        bottom = unionArea.bottom
    ) {
        data.forEachIndexed { index, datum ->
            // 描画するbarの座標を計算
            datum.value.forEach {
                val t = a * it.toFloat()
                val y = t + b
                val barTop = min(y, b)
                val barHeight = abs(t)
                val barLeft = xStart + barInterval * index
//            drawRect(
//                color = Pink,
//                topLeft = Offset(x = barLeft, y = barTop),
//                size = Size(width = barWidth, height = barHeight)
//            )
                drawCircle(
                    color = Pink,
                    radius = 5.dp.toPx(),
                    center = Offset(x = barLeft, y = barTop),
                )
//            drawRoundRect(
//                color = Pink,
//                topLeft = Offset.Zero,
//                size = Size(width = barWidth, height = barHeight),
//                cornerRadius = CornerRadius(10L),
//                style = Fill
//            )
                // ラベルの座標を計算
                val labelLayoutResult = dataLabelLayoutResults[index]
                val labelTop = dataLabelArea.top
                val labelLeft = barLeft + barWidth / 2f - labelLayoutResult.size.width / 2f
                // ラベルを描画
                drawText(
                    textLayoutResult = labelLayoutResult,
                    topLeft = Offset(labelLeft, labelTop)
                )
            }


//
//            // データ値の描画
//            val valueLayoutResult = dataValueLayoutResults[index]
//            val valueLeft = barLeft + (barWidth - valueLayoutResult.size.width) / 2
//            val valueTop = if (0f <= datum.value.toFloat()) barTop - valueLayoutResult.size.height
//            else barTop + barHeight
//            drawText(
//                textLayoutResult = valueLayoutResult,
//                topLeft = Offset(valueLeft, valueTop)
//            )
        }
    }
}

// grid値とgrid線を描画する
private fun <T> DrawScope.drawGrid(
    yAxisAttributes: YAxisAttributes,
    gridValueLayoutResults: List<TextLayoutResult>,
    gridLineArea: Rect,
    gridValueArea: Rect,
    attributes: BarChartAttributes<T>
) where T : Number, T : Comparable<T> {
    val yMin = yAxisAttributes.minValue
    val yMax = yAxisAttributes.maxValue

    // データ値を座標に変換する係数を計算 y = ax + b
    val yRange = yMax - yMin
    val a = (gridLineArea.top - gridLineArea.bottom) / yRange
    val b = (gridLineArea.bottom * yMax - gridLineArea.top * yMin) / yRange

    yAxisAttributes.gridList.forEachIndexed { index, gridValue ->
        val y = a * gridValue + b
        // grid線を描画する
        drawLine(
            color = attributes.gridLineColor,
            start = Offset(x = gridLineArea.left, y = y),
            end = Offset(x = gridLineArea.right, y = y)
        )
        // grid値を描画する
        val valueSize = gridValueLayoutResults[index].size
        val valueLeft = gridValueArea.right - valueSize.width
        val valueTop = y - valueSize.height / 2
        drawText(
            textLayoutResult = gridValueLayoutResults[index],
            topLeft = Offset(x = valueLeft, y = valueTop)
        )
    }
}

@Preview(widthDp = 400, heightDp = 400)
@Composable
private fun BarChartPreview1() {
    BarChart(
        data = listOf(
            Datum(listOf(1,12), "d1"),
            Datum(listOf(12,12),"d2"),
            Datum(listOf(11,12), "d3"),
            Datum(listOf(19,12), "d4"),
            Datum(listOf(10,12), "d5"),
            Datum(listOf(9,12), "d6")
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        attributes = BarChartAttributes(gridValueFormatPattern = ",##0円"),
    )
}
