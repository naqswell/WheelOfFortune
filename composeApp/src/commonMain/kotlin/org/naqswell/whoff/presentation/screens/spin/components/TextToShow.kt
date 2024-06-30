package org.naqswell.whoff.presentation.screens.spin.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextToShow(
    animationTrigger: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        AnimatedContent(targetState = animationTrigger) { content ->
            Text(
                modifier = modifier
                    .verticalScroll(rememberScrollState()),
                text = content,
                textAlign = TextAlign.Center,
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    lineHeight = 0.sp,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.Both
                    )
                )
            )
        }
    }
}