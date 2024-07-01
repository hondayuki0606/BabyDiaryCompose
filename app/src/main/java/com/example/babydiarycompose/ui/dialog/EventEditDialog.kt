package com.example.babydiarycompose.ui.dialog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.data.BreastMilkSelection
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.ui.theme.DialogBackDark
import com.example.babydiarycompose.ui.theme.DialogBackGray
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch

@Composable
fun EventEditDialog(
    event: EventData,
    selectedDate: String,
    volumeValue: (String) -> Unit,
    setShowDialog: (Boolean) -> Unit,
) {
    val viewModel: RecordingViewModel = hiltViewModel()
    val applicationContext = LocalContext.current
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
        BreastMilkDialog(
            eventName = event.eventName,
            selectedDate = event.yearAndMonthAndDay,
            hour = 12,
            minutes = 2,
            resIcon = event.imageUrl,
            uid = event.uid,
            setShowDialog = {
                breastfeedingDialog.value = it
            },
            resultValue = {
                breastfeedingDialog.value = it
            },
            editMode = true
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
            color = Color(0x00000000),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0x00000000))
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(DialogBackDark),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = event.eventName,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                        ),
                        modifier = Modifier
                            .background(DialogBackDark)
                            .wrapContentSize(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                val splits = selectedDate.split("/")
                val data = "${splits[0]}年${splits[1]}月${splits[2]}日"
                Text(
                    text = "日時 $data",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(DialogBackDark)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            datePickerDialog.value = true
                        }
                )

                if (event.eventName == Event.BREAST_MILK.event) {
                    if (event.eventDetail.contains("左")) {
                        val sort = getSortSymbol(event.eventDetail)
                        val eventDetail = event.eventDetail.split(sort)
                        val result = eventDetail[0].filter { it.isDigit() }
                        val leftTime = "${result}分"
                        Text(
                            text = "左乳  $leftTime",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .background(DialogBackDark)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    breastfeedingDialog.value = true
                                }
                        )
                    }

                    if (event.eventDetail.contains("右")) {
                        val sort = getSortSymbol(event.eventDetail)
                        val eventDetail = event.eventDetail.split(sort)
                        val result = eventDetail[1].filter { it.isDigit() }
                        val rightTime = "${result}分"
                        Text(
                            text = "右乳  $rightTime",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp,
                            ),
                            modifier = Modifier
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    breastfeedingDialog.value = true
                                }
                        )
                    }
                    val sort = getSortSymbol(event.eventDetail)
                    Text(
                        text = "順序  $sort",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                breastfeedingDialog.value = true
                            }
                    )
                }
                if (event.eventName == Event.MILK.event) {
                    Text(
                        text = "(量)  ${event.eventDetail}",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier
                            .background(DialogBackDark)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                quantityDialog.value = true
                            }
                    )
                }
                val memo = ""
                Text(
                    text = "メモ $memo",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier
                        .background(DialogBackDark)
                        .clickable(
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
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            // 写真のアクセス処理を行う
                        }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        setShowDialog(false)
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = DialogBackGray,
                        contentColor = DialogBackGray
                    )
                ) {
                    Text(text = "閉じる", color = Pink)
                }
                Spacer(modifier = Modifier.height(50.dp))
                val scope = rememberCoroutineScope()
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        scope.launch {
                            if (event.uid != null) {
                                viewModel.deleteEvent( event.uid)
                            }
                            setShowDialog(false)
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = DialogBackGray,
                        contentColor = DialogBackGray
                    )
                ) {
                    Text(text = "削除", color = Color.Red)
                }
            }
        }
    }
}

fun getSortSymbol(eventDetail: String): String {
    return if (eventDetail.contains(BreastMilkSelection.FROM_RIGHT.symbol)) {
        BreastMilkSelection.FROM_RIGHT.symbol
    } else if (eventDetail.contains(BreastMilkSelection.FROM_LEFT.symbol)) {
        BreastMilkSelection.FROM_LEFT.symbol
    } else if (eventDetail.contains(BreastMilkSelection.NO_ORDER.symbol)) {
        BreastMilkSelection.NO_ORDER.symbol
    } else {
        ""
    }
}
