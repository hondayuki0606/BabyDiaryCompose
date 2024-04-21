package com.example.babydiarycompose.ui.dialog

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun EventDetailDialog(
    selectedDate: String,
    volumeValue: (String) -> Unit,
    setShowDialog: (Boolean) -> Unit,
) {

    val datePickerDialog = remember { mutableStateOf(false) }
    // 日時ダイアログ
    if (datePickerDialog.value)
        DatePickerDialog(value = "", setShowDialog = {
            datePickerDialog.value = it
        }) {
            Log.i("datePickerDialog", "showDialog : $it")
        }
    // 母乳ダイアログ
    val breastfeedingDialog = remember { mutableStateOf(false) }
    if (breastfeedingDialog.value)
        EventTimeSettingDialog(
            eventName = "",
            resIcon = 0,
            selectedDate = selectedDate,
            setShowDialog = {
                breastfeedingDialog.value = it
            }
        )
    // 量ダイアログ
    val quantityDialog = remember { mutableStateOf(false) }
    val quantityDialogValue = remember { mutableStateOf(volumeValue) }
    if (quantityDialog.value)
        QuantityDialog(
            value = { },
            setShowDialog = {
                quantityDialog.value = it
            }
        )
    // メモダイアログ
    val memoDialog = remember { mutableStateOf(false) }
    if (memoDialog.value)
        MemoDialog(value = "", setShowDialog = {
            memoDialog.value = it
        }) {
            Log.i("memoDialog", "showDialog : $it")
        }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.Black
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "母乳",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 24.sp,
                            ),
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    val data = "1月9日 19時 45分"
                    Text(
                        text = "日時 $data",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            datePickerDialog.value = true
                        },
                    )
                    val leftTime = "5分"
                    Text(
                        text = "左乳  $leftTime",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            breastfeedingDialog.value = true
                        }
                    )
                    val rightTime = "なし"
                    Text(
                        text = "右乳  $rightTime",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            breastfeedingDialog.value = true
                        }
                    )

                    val sort = "左から"
                    Text(
                        text = "順序  $sort",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            breastfeedingDialog.value = true
                        }
                    )

                    Text(
                        text = "(量)  {state.uiState.volume}",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            quantityDialog.value = true
                        }
                    )

                    val memo = ""
                    Text(
                        text = "メモ $memo",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            memoDialog.value = true
                        }
                    )
                    val picture = ""
                    Text(
                        text = "写真 $picture",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            // 写真のアクセス処理を行う
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                setShowDialog(false)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "閉じる")
                        }
                    }
                }
            }
        }
    }
}