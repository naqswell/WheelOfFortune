package org.naqswell.whoff.presentation.screens.spin.state

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Stable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.naqswell.whoff.presentation.screens.spin.components.SpinWheelItemState
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Stable
data class SpinWheelState(
    val wheelItems: List<SpinWheelItemState>,

    val onItemSelectionStart: (item: SpinWheelItemState) -> Unit,
    val onItemSelectionEnd: (item: SpinWheelItemState, index: Int) -> Unit,

    val stopDuration: Duration = 3.seconds,
    val stopNbTurn: Float = 3f,
    private val rotationPerSecond: Float = 0.8f,
) {
    val rotation = Animatable(0f)

    fun onLaunch(coroutineScope: CoroutineScope) {
        val sectionToStop = wheelItems.indices.randomOrNull() ?: return

        coroutineScope.launch {
            val destinationDegree = getDegreeFromSectionWithRandom(wheelItems, sectionToStop)

            wheelItems.elementAtOrNull(sectionToStop)?.let { onItemSelectionStart(it) }

            rotation.animateTo(
                targetValue = rotation.value + (stopNbTurn * 360f) + destinationDegree + (360f - (rotation.value % 360f)),
                animationSpec = tween(
                    durationMillis = stopDuration.inWholeMilliseconds.toInt(),
                    easing = EaseOutQuad
                )
            )
            wheelItems.elementAtOrNull(sectionToStop)?.let { onItemSelectionEnd(it, sectionToStop) }
        }
    }

    private fun getDegreeFromSectionWithRandom(
        items: List<SpinWheelItemState>,
        section: Int
    ): Float {
        val pieDegree = 360f / items.size
        val exactDegree = pieDegree * section.times(-1)

        val pieReduced = pieDegree * 0.9f //to avoid stop near border
        val multiplier = if (Random.nextBoolean()) 1f else -1f //before or after exact degree
        val randomDegrees = Random.nextDouble(0.0, pieReduced / 2.0)
        return exactDegree + (randomDegrees.toFloat() * multiplier)
    }
}