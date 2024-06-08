package com.example.babydiarycompose.ui.dialog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.ui.theme.DialogBackDark
import com.example.babydiarycompose.ui.theme.DialogBackGray
import com.example.babydiarycompose.ui.theme.Pink

@Composable
fun EventEditDialog(
    event: Event,
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
            color = Color(0x00000000),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .background(Color(0x00000000))
                    .fillMaxSize()
            ) {
                val (eventTitle, dateTime, breastMilkLeft, breastMilkRight, selectionEdit, volumeEdit, memoEdit, pictureEdit, closeButton) = createRefs()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(DialogBackDark)
                        .constrainAs(eventTitle) {
                            top.linkTo(parent.top)
                            bottom.linkTo(dateTime.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = event.eventName,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier
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
                        fontSize = 24.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(DialogBackDark)
                        .constrainAs(dateTime) {
                            top.linkTo(eventTitle.bottom)
                            bottom.linkTo(breastMilkLeft.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            datePickerDialog.value = true
                        },
                )
                if (event.eventName == "母乳") {
                    if (event.eventDetail.contains("左")) {
                        val leftTime = "5分"
                        Text(
                            text = "左乳  $leftTime",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .constrainAs(breastMilkLeft) {
                                    top.linkTo(dateTime.bottom)
                                    bottom.linkTo(breastMilkRight.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    breastfeedingDialog.value = true
                                }
                        )
                    }

                    if (event.eventDetail.contains("右")) {
                        val rightTime = "なし"
                        Text(
                            text = "右乳  $rightTime",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 24.sp,
                            ),
                            modifier = Modifier
                                .constrainAs(breastMilkRight) {
                                    top.linkTo(breastMilkLeft.bottom)
                                    bottom.linkTo(selectionEdit.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    breastfeedingDialog.value = true
                                }
                        )
                    }
                    var sort = ""
                    if (event.eventDetail.contains("←")) {
                        sort = "左から"
                    }
                    if (event.eventDetail.contains("→")) {
                        sort = "右から"
                    }
                    if (event.eventDetail.contains("/")) {
                        sort = "順序なし"
                    }
                    Text(
                        text = "順序  $sort",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier
                            .constrainAs(selectionEdit) {
                                top.linkTo(breastMilkRight.bottom)
                                bottom.linkTo(volumeEdit.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                breastfeedingDialog.value = true
                            }
                    )
                }
                if (event.eventName == "ミルク") {
                    Text(
                        text = "(量)  10ml",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                        ),
                        modifier = Modifier
                            .constrainAs(volumeEdit) {
                                top.linkTo(selectionEdit.bottom)
                                bottom.linkTo(memoEdit.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
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
                        fontSize = 24.sp,
                    ),
                    modifier = Modifier
                        .constrainAs(memoEdit) {
                            top.linkTo(volumeEdit.bottom)
                            bottom.linkTo(pictureEdit.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
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
                        fontSize = 24.sp,
                    ),
                    modifier = Modifier
                        .constrainAs(pictureEdit) {
                            top.linkTo(parent.top)
                            bottom.linkTo(closeButton.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
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
                        .height(50.dp)
                        .constrainAs(closeButton) {
                            top.linkTo(pictureEdit.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    onClick = {
                        setShowDialog(false)
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = DialogBackGray,
                        contentColor = DialogBackGray
                    )
                ) {
                    Text(text = "閉じる")
                }
            }
        }
    }
}