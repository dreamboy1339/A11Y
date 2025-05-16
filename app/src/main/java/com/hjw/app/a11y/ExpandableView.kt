package com.hjw.app.a11y

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableView(title: String) {
    var expandedState by remember { mutableStateOf(false) }
    var rotationState by remember { mutableFloatStateOf(0.0f) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expandedState = !expandedState
            }
            .padding(16.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = title
                // 접근성 상태 설명 추가
                stateDescription = if (expandedState) "확장됨" else "축소됨"
                // 라이브 리전으로 설정하여 상태 변경 알림
                liveRegion = LiveRegionMode.Polite
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .weight(1f)
                .clearAndSetSemantics { }
        )

        Icon(
            imageVector = if (expandedState) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowUp
            },
            contentDescription = if (expandedState) {
                "접기"
            } else {
                "펼치기"
            },
            modifier = Modifier
                .rotate(rotationState)
                .clearAndSetSemantics { }
        )
    }
}
