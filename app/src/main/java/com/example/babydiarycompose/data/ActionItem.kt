package com.example.babydiarycompose.data

import android.media.Image
import java.io.Serializable
import java.time.LocalDateTime

data class ActionItem(
    // 時間
    val time: LocalDateTime,
    // アイコン
    val icon: Image?,
    // アクション名
    val name: String,
    // 詳細
    val detail: String
) : Serializable
