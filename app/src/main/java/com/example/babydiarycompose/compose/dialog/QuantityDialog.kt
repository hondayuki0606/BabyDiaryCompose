package com.example.babydiarycompose.compose.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun QuantityDialog(
    value: (String) -> Unit,
    setShowDialog: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (titleText, verticalScroll, closeButton) = createRefs()
            Text(
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(0.7f)
                    .background(Color(0xFF272727))
                    .padding(10.dp),
                color = Color.White,
                text = "ミルク"
            )
            LazyColumn(
                modifier = Modifier
                    .constrainAs(verticalScroll) {
                        top.linkTo(titleText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
                    .background(Color(0xFF272727))
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth(0.7f)
            ) {
                val quantity =
                    arrayListOf(
                        "0m",
                        "5m",
                        "10m",
                        "20m",
                        "30m",
                        "40m",
                        "50m",
                        "60m",
                        "70m",
                        "80m",
                        "90m",
                        "100m",
                        "110m",
                        "120m",
                        "130m",
                        "140m",
                        "150m",
                        "160m",
                        "170m",
                        "180m",
                        "190m",
                        "200m",
                    )
                items(quantity) {
                    Column {
                        TextButton(
                            onClick = { },
                            modifier = Modifier
                                .background(Color(0xFF272727))
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .clickable {
                                        setShowDialog(false)
                                        value(it)
                                    }
                                    .padding(10.dp),
                                color = Color.White,
                                text = it
                            )
                        }
                    }
                }
            }
            TextButton(
                onClick = {
                    setShowDialog(false)
                },
                modifier = Modifier
                    .background(Color(0xFF272727))
                    .constrainAs(closeButton) {
                        top.linkTo(verticalScroll.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    color = Color(0xFFEC7786),
                    text = "キャンセル",
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .background(Color(0xFF272727))
                )
            }
        }
    }
}