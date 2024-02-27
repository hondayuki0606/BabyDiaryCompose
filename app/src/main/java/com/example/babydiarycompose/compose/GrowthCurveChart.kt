package com.example.babydiarycompose.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.babydiarycompose.data.BarChartAttributes
import com.example.babydiarycompose.data.Datum
import com.example.babydiarycompose.data.GrowthHeightData
import com.example.babydiarycompose.data.GrowthWeightData
import com.example.babydiarycompose.data.Item
import com.example.babydiarycompose.data.YAxisAttributes
import com.example.babydiarycompose.data.makeYAxisAttributes
import com.example.babydiarycompose.utils.Flower
import com.example.babydiarycompose.utils.Teal200
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.max
import kotlin.math.min


// grid値の文字列とy軸の間のpadding
private val PADDING = 8.dp

@Composable
fun GrowthCurveChart(
    growthHeight: List<GrowthHeightData>,
    growthWeight: List<GrowthWeightData>,
    cmList: List<String>,
    ageList: List<String>,
    weightList: List<String>,
    modifier: Modifier = Modifier,
    attributes: BarChartAttributes = BarChartAttributes(),
) {
    BoxWithConstraints(modifier = modifier) {
        // 文字列のサイズの計測器を準備する
        val textMeasurer = rememberTextMeasurer()
        // 年月のデータラベルを計測する
        val dataLabelLayoutResults = measureGrowthDataLabel(
            ageList = ageList,
            attributes = attributes,
            textMeasurer = textMeasurer
        )
        // データラベルを計測する
        val dataWeightLabelLayoutResults = measureGrowthDataLabel(
            ageList = weightList,
            attributes = attributes,
            textMeasurer = textMeasurer
        )
        // データラベルの最大の高さを取得する
        val maxDataLabelHeight = dataLabelLayoutResults.maxOfOrNull {
            it.size.height
        } ?: 0
        // データ値を表す文字列の最大の高さを取得する
        val maxDataValueHeight = dataLabelLayoutResults.maxOfOrNull {
            it.size.height
        } ?: 0

        // y軸の範囲とgridを求める
        val yAxisAttributes =
            makeYAxisAttributes(maxValue = 15F, gridInterval = 1F, attributes = attributes)

        // grid値の左を表す文字列を計測する
        val gridLeftValueLayoutResults = measureGridValue(
            yAxisAttributes = yAxisAttributes,
            attributes = attributes,
            textMeasurer = textMeasurer
        )

        // grid値の右を表す文字列を計測する
        val gridRightValueLayoutResults = measureWeightDataValue(
            cmList = cmList,
            attributes = attributes,
            textMeasurer = textMeasurer
        )

        // Grid値の最大の幅を取得する
        val maxGridValueWidth = gridLeftValueLayoutResults.maxOfOrNull {
            it.size.width
        } ?: 0

        val heightData = arrayListOf(
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
            (Item("b", 5)),
        )

        val weightData = arrayListOf(
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
            (Item("a", 1)),
        )

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

        val plotArea = Rect(
            left = axisArea.left,
            top = axisArea.top + maxDataValueHeight,
            right = axisArea.right,
            bottom = axisArea.bottom - maxDataValueHeight
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

            // 身長のデータを描画する
            drawHeightData(
                data = heightData,
                yAxisAttributes = yAxisAttributes,
                plotArea = plotArea,
                dataLabelArea = dataLabelArea,
                dataValueArea = dataValueArea,
                attributes = attributes
            )

            // 体重のデータを描画する
            drawWeightData(
                data = weightData,
                yAxisAttributes = yAxisAttributes,
                plotArea = plotArea,
                dataLabelArea = dataLabelArea,
                dataValueArea = dataValueArea,
                attributes = attributes
            )

            // X軸のラベルを描写する
            drawXLabel(
                dataLabelLayoutResults = dataLabelLayoutResults,
                plotArea = plotArea,
                dataLabelArea = dataLabelArea,
                dataValueArea = dataValueArea,
                attributes = attributes
            )

            // grid値とgrid線を描画する
            drawGridLine(
                yAxisAttributes = yAxisAttributes,
                gridLineArea = gridLineArea,
                attributes = attributes
            )

            // 左のY軸のラベルを描画する
            drawLeftYLabel(
                yAxisAttributes = yAxisAttributes,
                gridValueLayoutResults = gridLeftValueLayoutResults,
                gridLineArea = gridLineArea,
                gridValueArea = gridValueArea,
            )

            // 右のY軸のラベルを描画する
            drawRightYLabel(
                yAxisAttributes = yAxisAttributes,
                gridValueLayoutResults = gridRightValueLayoutResults,
                gridLineArea = gridLineArea,
                gridValueArea = gridValueArea,
            )

            // 身長ラベルを描画
            drawGrowthCurveHeightLabel(
                gridLineArea = gridLineArea,
                gridValueArea = gridValueArea,
                attributes = attributes,
                textMeasurer = textMeasurer
            )

            // 体重ラベルを描画
            drawGrowthCurveWeightLabel(
                gridLineArea = gridLineArea,
                gridValueArea = gridValueArea,
                attributes = attributes,
                textMeasurer = textMeasurer
            )
        }
    }
}

// データラベルを計測する
private fun measureGrowthDataLabel(
    ageList: List<String>,
    attributes: BarChartAttributes,
    textMeasurer: TextMeasurer
): List<TextLayoutResult> {
    val textLayoutResults = mutableListOf<TextLayoutResult>()
    ageList.forEach { label ->
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
private fun measureGridValue(
    yAxisAttributes: YAxisAttributes,
    attributes: BarChartAttributes,
    textMeasurer: TextMeasurer
): List<TextLayoutResult> {
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
private fun measureWeightDataValue(
    cmList: List<String>,
    attributes: BarChartAttributes,
    textMeasurer: TextMeasurer
): List<TextLayoutResult> {
    val textLayoutResults = mutableListOf<TextLayoutResult>()
    val formatter = attributes.dataValueFormatPattern?.let {
        DecimalFormat(it)
    } ?: NumberFormat.getInstance()
    cmList.forEach {
//        val value = formatter.format(it.value.toFloat())
        textLayoutResults.add(
            textMeasurer.measure(
                text = AnnotatedString(it),
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
private fun DrawScope.drawAxis(
    area: Rect,
    attributes: BarChartAttributes
) {
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
private fun DrawScope.drawHeightData(
    data: List<Item>,
    yAxisAttributes: YAxisAttributes,
    plotArea: Rect,
    dataLabelArea: Rect,
    dataValueArea: Rect,
    attributes: BarChartAttributes
) {
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
        data.forEachIndexed { index, it ->
            // 描画するbarの座標を計算
            val t = a * it.value.toFloat()
            val y = t + b
            val barTop = min(y, b)
            val barLeft = xStart + barInterval * index

            val left = 21
            println("left = ${barLeft}")
            drawCircle(
                color = Flower,
                radius = 2.dp.toPx(),
                center = Offset(x = barLeft, y = barTop),
            )
        }
    }
}

// データを描画する
private fun DrawScope.drawWeightData(
    data: List<Item>,
    yAxisAttributes: YAxisAttributes,
    plotArea: Rect,
    dataLabelArea: Rect,
    dataValueArea: Rect,
    attributes: BarChartAttributes
) {
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
        data.forEachIndexed { index, it ->
            // 描画するbarの座標を計算
            val t = a * it.value.toFloat()
            val y = t + b
            val barTop = min(y, b)
            val barLeft = xStart + barInterval * index

            val left = 21
            println("left = ${barLeft + (left)}")
            drawCircle(
                color = Flower,
                radius = 2.dp.toPx(),
                center = Offset(x = barLeft + left, y = barTop),
            )
        }
    }
}

// X軸のラベルを描画する
private fun DrawScope.drawXLabel(
    dataLabelLayoutResults: List<TextLayoutResult>,
    plotArea: Rect,
    dataLabelArea: Rect,
    dataValueArea: Rect,
    attributes: BarChartAttributes
) {
    // データ値を座標に変換する係数を計算 y = ax + b
    val barWidth = attributes.barWidth.toPx()
    // clipの範囲をプロット領域とラベル領域、データ値領域の全てを含むようにする
    val unionArea = plotArea.union(dataLabelArea).union(dataValueArea)
    clipRect(
        left = unionArea.left,
        top = unionArea.top,
        right = unionArea.right,
        bottom = unionArea.bottom
    ) {
        dataLabelLayoutResults.forEachIndexed { index, _ ->
            // 描画するbarの座標を計算
            val allLabelArea = dataLabelArea.right - dataLabelArea.left
            val weight = allLabelArea / dataLabelLayoutResults.size
            // ラベルの座標を計算
            val labelLayoutResult = dataLabelLayoutResults[index]
            val labelTop = dataLabelArea.top
            val labelLeft = (weight * index) + barWidth / 2f - labelLayoutResult.size.width / 2f
            // ラベルを描画
            drawText(
                textLayoutResult = labelLayoutResult,
                topLeft = Offset(labelLeft, labelTop)
            )
        }
    }
}

// grid値とgrid線を描画する
private fun DrawScope.drawGridLine(
    yAxisAttributes: YAxisAttributes,
    gridLineArea: Rect,
    attributes: BarChartAttributes
) {
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
    }
}


// grid値とgrid線を描画する
private fun DrawScope.drawLeftYLabel(
    yAxisAttributes: YAxisAttributes,
    gridValueLayoutResults: List<TextLayoutResult>,
    gridLineArea: Rect,
    gridValueArea: Rect,
) {
    val yMin = yAxisAttributes.minValue
    val yMax = yAxisAttributes.maxValue

    // データ値を座標に変換する係数を計算 y = ax + b
    val yRange = yMax - yMin
    val a = (gridLineArea.top - gridLineArea.bottom) / yRange
    val b = (gridLineArea.bottom * yMax - gridLineArea.top * yMin) / yRange

    yAxisAttributes.gridList.forEachIndexed { index, gridValue ->
        val y = a * gridValue + b
        // grid値を描画する
        val valueSize = gridValueLayoutResults[index].size
        val valueLeft = gridValueArea.right - valueSize.width
        val valueTop = y - valueSize.height / 2
        if (index < 11) {
            drawText(
                color = Flower,
                textLayoutResult = gridValueLayoutResults[index],
                topLeft = Offset(x = valueLeft, y = valueTop)
            )
        }
    }
}

// grid値とgrid線を描画する
private fun DrawScope.drawRightYLabel(
    yAxisAttributes: YAxisAttributes,
    gridValueLayoutResults: List<TextLayoutResult>,
    gridLineArea: Rect,
    gridValueArea: Rect,
) {
    val yMin = yAxisAttributes.minValue
    val yMax = yAxisAttributes.maxValue

    // データ値を座標に変換する係数を計算 y = ax + b
    val yRange = yMax - yMin
    val a = (gridLineArea.top - gridLineArea.bottom) / yRange
    val b = (gridLineArea.bottom * yMax - gridLineArea.top * yMin) / yRange

    yAxisAttributes.gridList.forEachIndexed { index, gridValue ->
        val y = (a) * gridValue + b
        // grid値を描画する
        if (index < 9) {
            val valueSize = gridValueLayoutResults[index].size
            val valueLeft = gridValueArea.right - valueSize.width
            val valueTop = y - valueSize.height / 2
            drawText(
                color = Flower,
                textLayoutResult = gridValueLayoutResults[index],
                topLeft = Offset(x = valueLeft + 200, y = valueTop)
            )
        }
    }
}

private fun DrawScope.drawGrowthCurveHeightLabel(
    gridLineArea: Rect,
    gridValueArea: Rect,
    attributes: BarChartAttributes,
    textMeasurer: TextMeasurer
) {
    val valueLeft = gridValueArea.left
    val valueBottom = gridLineArea.bottom
    drawText(
        color = Flower,
        textLayoutResult = textMeasurer.measure(
            text = AnnotatedString("体重"),
            style = TextStyle(
                color = attributes.dataLabelTextColor,
                fontSize = 24.sp
            )
        ),
        topLeft = Offset(x = valueLeft + 800, y = valueBottom - 50)
    )
}

private fun DrawScope.drawGrowthCurveWeightLabel(
    gridLineArea: Rect,
    gridValueArea: Rect,
    attributes: BarChartAttributes,
    textMeasurer: TextMeasurer,
) {
    val valueLeft = gridValueArea.left
    val valueTop = gridLineArea.top
    drawText(
        color = Teal200,
        textLayoutResult = textMeasurer.measure(
            text = AnnotatedString("身長"),
            style = TextStyle(
                color = attributes.dataLabelTextColor,
                fontSize = 24.sp
            )
        ),
        topLeft = Offset(
            x = valueLeft + 100, y = valueTop + 50
        )
    )
}

@Preview(widthDp = 400, heightDp = 400)
@Composable
private fun BarChartPreview1() {
    BarChart(
        data = listOf(
            Datum(listOf(Item("a", 1), Item("b", 24)), "2/11"),
            Datum(listOf(Item("a", 1), Item("a", 21), Item("a", 23)), "2/12"),
            Datum(listOf(Item("a", 2), Item("a", 3)), "2/13"),
            Datum(listOf(Item("a", 12)), "2/14"),
            Datum(listOf(Item("a", 12)), "2/15"),
            Datum(listOf(Item("a", 10)), "2/16"),
            Datum(listOf(Item("a", 24)), "2/17")
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}
