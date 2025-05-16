package com.hjw.app.a11y

import android.content.Context
import android.os.Build
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.focused
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OptimalFocus() {
    Image(
        imageVector = Icons.Default.Star,
        contentDescription = null,  // 장식적 이미지는 contentDescription 없음
        modifier = Modifier.semantics {
            isTraversalGroup = false  // 탐색 경로에서 제외
        }
    )


    // 중요 컨트롤에는 명시적 접근성 포커스 제어
    Button(
        onClick = { /* 동작 */ },
        modifier = Modifier.semantics {
            focused = true  // 명시적으로 포커스 설정
        }
    ) {
        Text("중요 동작")
    }
}

@Composable
fun TextList() {
    Column {
        Text(
            text = "첫 번째 항목",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.semantics {
                traversalIndex = 1f  // 첫 번째로 포커스
            }
        )

        Text(
            text = "세 번째 항목",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.semantics {
                traversalIndex = 3f  // 세 번째로 포커스
            }
        )

        Text(
            text = "두 번째 항목",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.semantics {
                traversalIndex = 2f  // 두 번째로 포커스
            }
        )
    }
}

@Composable
fun SpeakList() {
    // 복잡한 약어나 발음 최적화
    Text(
        text = "FAQ",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.semantics {
            contentDescription = "자주 묻는 질문"
        }
    )

    // 상태 정보 제공
    var isChecked by remember { mutableStateOf(false) }
    Checkbox(
        checked = isChecked,
        onCheckedChange = { isChecked = it },
        modifier = Modifier.semantics {
            stateDescription = if (isChecked) "선택됨" else "선택되지 않음"
        }
    )

    // 라이브 리전 설정으로 중요 알림 발화
    Text(
        text = "새로운 메시지가 도착했습니다",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.semantics {
            liveRegion = LiveRegionMode.Polite
        }
    )
}

@Composable
fun LoadingExample() {
    val context = LocalContext.current
    val accessibilityManager = context.getSystemService(AccessibilityManager::class.java)
    val coroutineScope = rememberCoroutineScope()

    var isDataLoaded by remember { mutableStateOf(false) }
    // 데이터 로딩 후 접근성 알림
    LaunchedEffect(isDataLoaded) {
        if (isDataLoaded && accessibilityManager?.isEnabled == true) {
            coroutineScope.launch {
                delay(100) // UI 업데이트 이후 약간의 지연

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    sendAccessibilityEvent(
                        context = context,
                        message = "데이터 로드가 완료되었습니다"
                    )
                }
            }
        }
    }

    // 로딩 인디케이터에 접근성 정보 추가
    CircularProgressIndicator(
        modifier = Modifier.progressSemantics()
    )

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(2000)
            isDataLoaded = true
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
fun sendAccessibilityEvent(context: Context, message: String) {
    val accessibilityManager = context.getSystemService(AccessibilityManager::class.java)
    if (accessibilityManager?.isEnabled == true) {
        val event = AccessibilityEvent().apply {
            eventType = AccessibilityEvent.TYPE_ANNOUNCEMENT
            className = javaClass.name
            packageName = context.packageName
            text.add(message)
        }
        accessibilityManager.sendAccessibilityEvent(event)
    }
}
