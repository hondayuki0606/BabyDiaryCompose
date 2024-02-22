package com.example.babydiarycompose.data

import java.lang.Float.max
import java.lang.Float.min

data class YAxisAttributes(val minValue: Float, val maxValue: Float, val gridList: List<Float>)

fun makeYAxisAttributes(
    gridInterval: Float,
    maxValue: Float,
    attributes: BarChartAttributes = BarChartAttributes(),
    gridCount: Float = 7f // 目標とするgrid線の数。実際にはこれより小さくなる
): YAxisAttributes {


    // 棒グラフのためy軸の範囲には必ず0を含むようにする
    val minValue = min(0f, 0f)
//    val maxValue = max(0f, 15f)

    val range = maxValue - minValue
    // 範囲が0だとグラフは書けないので(0,1)の範囲に変更する
    if (range == 0f) return YAxisAttributes(0f, 1f, emptyList())

    // grid間隔を計算する。1, 2, 5, 10, 20, 50, 100, ... 等から選ぶ。
    // grid間隔はgrid線がgridCount個程度になるように調整する
    var gridIntervalOrder = 1f
    // まずはgrid線がgridCount個以上になるようにする
    while (true) {
        if (gridCount <= range / gridIntervalOrder + 1) break
        // grid幅の桁を一つ下げる
        gridIntervalOrder /= 10f
    }
//    val gridInterval = 1F
    // grid位置を求め配列に追加していく
    val gridList = mutableListOf<Float>()
    val start = (minValue / gridInterval).toInt()
    val end = (maxValue / gridInterval).toInt() + 1
    for (i in start until end) {
        gridList.add(i * gridInterval)
    }
    return YAxisAttributes(minValue, maxValue, gridList)
}