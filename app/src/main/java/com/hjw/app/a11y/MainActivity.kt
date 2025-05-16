package com.hjw.app.a11y

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.text
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw.app.a11y.ui.theme.A11YTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A11YTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Log.d("fog", "onCreate: innerPadding = $innerPadding")

                    val contentPadding = PaddingValues(
                        start = 16.dp,
                        top = innerPadding.calculateTopPadding(),
                        end = 16.dp,
                        bottom = innerPadding.calculateBottomPadding()
                    )

                    Column(modifier = Modifier.padding(contentPadding)) {
                        BasicA11y(name = "A11J", contentDescription = "내 프로필")
                        Spacer(modifier = Modifier.size(16.dp))
                        Profile(name = "나는 개발자", role = "안드로이드 개발자", contentDescription = "내 프로필")
                        Spacer(modifier = Modifier.size(16.dp))
                        SwipeToDismissDemo(
                            item = "todo",
                            onDelete = { }
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        ControllingTextRepresentation()
                        Spacer(modifier = Modifier.size(16.dp))
                        NotificationSettings()
                    }
                }
            }
        }
    }
}

@Composable
fun BasicA11y(
    name: String,
    contentDescription: String = ""
) {
    Text(
        text = "Hello $name!",
        modifier = Modifier
            .semantics {
                heading()
                this.contentDescription = contentDescription // 기본 접근성 설정.
            }
    )
}

@Composable
fun Profile(
    name: String,
    role: String,
    contentDescription: String
) {
    Row(
        modifier = Modifier
            .semantics(mergeDescendants = true) { // mergeDescendants 를 활용 포커스 묶음 + 접근성 설정.
                this.contentDescription = contentDescription
            },
    ) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = name,
                modifier = Modifier.semantics {
                    heading()
                }
            )
            Text(text = role, modifier = Modifier)
        }
    }
}

@Composable
fun ControllingTextRepresentation() {
    Text(
        text = "₩1,234,567",
        modifier = Modifier.semantics {
            // 스크린리더가 읽을 대체 텍스트 제공
            text = AnnotatedString("1,234,567원")
        }
    )
}

@Composable
fun NotificationSettings() {
    // 알림 설정
    var notificationsEnabled by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .semantics(mergeDescendants = true) {
                role = Role.Switch
                toggleableState = if (notificationsEnabled) {
                    ToggleableState.On
                } else {
                    ToggleableState.Off
                }
                stateDescription = if (notificationsEnabled) "알림 활성화됨" else "알림 비활성화됨"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("알림 설정", modifier = Modifier.weight(1f))
        Switch(
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    A11YTheme {
        BasicA11y(name = "Android")
    }
}
