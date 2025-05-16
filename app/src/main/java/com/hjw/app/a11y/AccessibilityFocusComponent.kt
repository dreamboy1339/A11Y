package com.hjw.app.a11y

import androidx.compose.foundation.focusable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.delay

@Composable
fun AccessibilityFocusComponent(
    isDialogVisible: Boolean,
    onDismiss: () -> Unit,
    onAction: () -> Unit
) {
    // 포커스 요청 객체 생성
    val focusRequester = remember { FocusRequester() }
    val haptic = LocalHapticFeedback.current

    // 다이얼로그 표시 여부에 따라 포커스 설정
    LaunchedEffect(isDialogVisible) {
        if (isDialogVisible) {
            // 약간의 지연 후 포커스 요청 (애니메이션 완료 후)
            delay(300)
            focusRequester.requestFocus()
        }
    }

    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    "중요 알림",
                    modifier = Modifier.semantics { heading() }
                )
            },
            text = { Text("이 작업을 진행하시겠습니까?") },
            confirmButton = {
                Button(
                    onClick = onAction,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .focusable()
                        .onFocusChanged { state ->
                            if (state.isFocused) {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            }
                        }
                ) {
                    Text("확인")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("취소")
                }
            }
        )
    }
}
