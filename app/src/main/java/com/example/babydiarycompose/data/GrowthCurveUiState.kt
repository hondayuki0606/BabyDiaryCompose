package com.example.babydiarycompose.data

data class GrowthCurveUiState(
    val ageList: List<String>,
    val weightList: List<String>,
    val cmList: List<String>,
    val wightList:List<GrowthWeightData>,
    val heightList:List<GrowthHeightData>,
    val tabList: List<String>
)