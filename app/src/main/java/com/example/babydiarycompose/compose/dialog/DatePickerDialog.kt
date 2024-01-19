package com.example.babydiarycompose.compose.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.babydiarycompose.compose.NumberPicker

@Composable
fun DatePickerDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                NumberPicker(
                    state = remember { mutableStateOf(9) },
                    range = 0..10,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}