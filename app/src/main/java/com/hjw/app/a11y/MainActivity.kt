package com.hjw.app.a11y

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
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
                    Column(modifier = Modifier.padding(innerPadding)) {
                        BasicA11y(name = "A11J", contentDescription = "내 프로필")
                        Profile(name = "나는 개발자", role = "안드로이드 개발자", contentDescription = "내 프로필")
                    }
                }
            }
        }
    }
}

@Composable
fun BasicA11y(
    name: String,
    modifier: Modifier = Modifier,
    contentDescription: String = ""
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .padding(16.dp)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    A11YTheme {
        BasicA11y(name = "Android")
    }
}
