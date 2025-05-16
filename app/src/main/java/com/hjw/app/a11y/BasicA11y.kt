package com.hjw.app.a11y

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics

@Composable
fun BasicA11y(
    name: String,
    contentDescription: String = ""
) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.Companion
            .semantics {
                heading()
                this.contentDescription = contentDescription // 기본 접근성 설정.
            }
    )
}
