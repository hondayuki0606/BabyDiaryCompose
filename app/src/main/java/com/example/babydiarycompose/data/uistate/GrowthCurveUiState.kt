package com.example.babydiarycompose.data.uistate

import com.example.babydiarycompose.data.GrowthHeightData
import com.example.babydiarycompose.data.GrowthWeightData

data class GrowthCurveUiState(
    val ageList: List<String>,
    val weightList: List<String>,
    val cmList: List<String>,
    val growthWeight:List<GrowthWeightData>,
    val growthHeight:List<GrowthHeightData>,
    val tabList: List<String>
)