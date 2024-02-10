package com.example.babydiarycompose.data

import java.lang.Float.max
import java.lang.Float.min

data class YAxisAttributes(val minValue: Float, val maxValue: Float)
fun <T> makeYAxisAttributes(
    data: List<Datum<T>> = emptyList(),
    attributes: BarChartAttributes<T> = BarChartAttributes()
): YAxisAttributes where T : Number, T : Comparable<T> {
    // チャートのy軸の範囲を決める
    val yMinOrNull = attributes.yMin ?: run { data.minOfOrNull { it.value } }
    val yMin = yMinOrNull?.toFloat() ?: 0f
    val yMaxOrNull = attributes.yMax ?: run { data.maxOfOrNull { it.value } }
    val yMax = yMaxOrNull?.toFloat() ?: 0f

    // 棒グラフのためy軸の範囲には必ず0を含むようにする
    val minValue = min(0f, yMin)
    val maxValue = max(0f, yMax)

    val range = maxValue - minValue
    // 範囲が0だとグラフは書けないので(0,1)の範囲に変更する
    if (range == 0f) return YAxisAttributes(0f, 1f)
    return YAxisAttributes(minValue, maxValue)
}