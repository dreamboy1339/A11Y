package com.hjw.app.a11y

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString

@Composable
fun ControllingTextRepresentation() {
    Text(
        text = "₩1,234,567",
        modifier = Modifier.Companion.semantics {
            // 스크린리더가 읽을 대체 텍스트 제공
            text = AnnotatedString("1,234,567원")
        }
    )
}
