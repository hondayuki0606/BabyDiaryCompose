package com.example.babydiarycompose.data

data class Datum<T>(val value: List<T>, val label: String)
        where T : Number, T : Comparable<T>

data class Item(val event:String,val value:Int)