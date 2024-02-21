package com.example.babydiarycompose.data

data class GrowthCurveUiState(
    val ageList: List<String>,
    val weightList: List<String>,
    val cmList: List<String>,
    val recordList:List<GrowthData>,
    val tabList: List<String>
)