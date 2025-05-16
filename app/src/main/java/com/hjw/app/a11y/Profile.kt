package com.hjw.app.a11y

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun Profile(
    name: String,
    role: String,
    contentDescription: String
) {
    Row(
        modifier = Modifier.Companion
            .semantics(mergeDescendants = true) { // mergeDescendants 를 활용 포커스 묶음 + 접근성 설정.
                this.contentDescription = contentDescription
            },
    ) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.Companion.size(48.dp)
        )

        Spacer(modifier = Modifier.Companion.width(16.dp))

        Column {
            Text(
                text = name,
                modifier = Modifier.Companion.semantics {
                    heading()
                }
            )
            Text(text = role, modifier = Modifier.Companion)
        }
    }
}
