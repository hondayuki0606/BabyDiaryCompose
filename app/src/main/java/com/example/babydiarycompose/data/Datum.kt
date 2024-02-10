package com.example.babydiarycompose.data

data class Datum<T>(val value: T, val label: String)
        where T : Number, T : Comparable<T>