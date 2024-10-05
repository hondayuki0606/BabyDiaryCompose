package com.example.babydiarycompose.ui.dialog

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.compose.ListItemPicker
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.ui.theme.DialogBackGray
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyDialog(
    setShowDialog: (Boolean) -> Unit
) {
    val currentDateTime = LocalDateTime.now()
    ZonedDateTime.of(currentDateTime, ZoneId.of("Asia/Tokyo"))
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0x00000000),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .background(Color(0x00000000))
                    .fillMaxSize()
            ) {
                val (dailyTitle, picker, buttonArea) = createRefs()
                Text( modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .background(DialogBackGray)
                    .constrainAs(dailyTitle) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    text = "育児日記（2024年10月5日）")
                TextField(
                    value = "text.value",
                    onValueChange = {"text.value=it"},
                    //以下注目
                    placeholder = { Text(text = "入力内容に関するテキスト")}
                )

                Column(modifier = Modifier
                    .constrainAs(buttonArea) {
                        top.linkTo(picker.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Button(
                        onClick = {

                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = DialogBackGray,
                            contentColor = DialogBackGray
                        )
                    ) {
                        Text(text = "キャンセル", color = Pink)
                    }
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
                        Text(text = "完了", color = Pink)
                    }
                }
            }
        }
    }
}