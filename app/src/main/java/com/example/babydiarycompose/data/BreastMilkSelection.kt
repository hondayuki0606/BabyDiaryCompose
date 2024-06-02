package com.example.babydiarycompose.data

enum class BreastMilkSelection(val text: String, val symbol: String) {
    NO_ORDER("順序なし", " / "),
    FROM_RIGHT("右から", " ← "),
    FROM_LEFT("左から", " → "),
}