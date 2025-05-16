package com.hjw.app.a11y

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState

@Composable
fun NotificationSettings() {
    // 알림 설정
    var notificationsEnabled by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.Companion
            .semantics(mergeDescendants = true) {
                role = Role.Companion.Switch
                toggleableState = if (notificationsEnabled) {
                    ToggleableState.On
                } else {
                    ToggleableState.Off
                }
                stateDescription = if (notificationsEnabled) "알림 활성화됨" else "알림 비활성화됨"
            },
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        Text("알림 설정", modifier = Modifier.Companion.weight(1f))
        Switch(
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )
    }
}
