package com.example.babydiarycompose.data

data class Datum<T>(val value: List<T>, val label: String)
// where　T:NumberにするとTには数値しか入れれなくなる、
// where T:Comparable<T>にするとTは同じ型しか入れれなくなるInt,Floatと別々の引数はいれることができない
// 制約をかける必要がないのでコメントアウトする
//        where T : Number, T : Comparable<T>

data class Item(val event:String,val value:Int)