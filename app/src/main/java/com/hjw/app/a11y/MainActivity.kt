package com.hjw.app.a11y

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    A11YTheme {
        BasicA11y(name = "Android")
    }
}
