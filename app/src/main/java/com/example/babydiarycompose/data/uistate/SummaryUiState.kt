package com.example.babydiarycompose.data.uistate

import com.example.babydiarycompose.data.Datum

data class SummaryUiState(
    val tabList: List<String>,
    val datumList: List<Datum>
)