package com.hjw.app.a11y

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun SwipeToDismissDemo(
    item: String,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .semantics {
                contentDescription = "항목: $item"
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "삭제",
                        action = {
                            onDelete()
                            true
                        }
                    )
                )
            }
    ) {
        Text(text = item, modifier = Modifier.padding(16.dp))
    }
}
