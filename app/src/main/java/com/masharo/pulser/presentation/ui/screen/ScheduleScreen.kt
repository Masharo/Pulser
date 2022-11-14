package com.masharo.pulser.presentation.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masharo.pulser.presentation.vm.ScheduleViewModel
import kotlin.math.roundToInt

@Composable
fun ScheduleScreen() {

//    val transformableState = TransformableState { zoomChange, _, _ ->
//        state.visibleCount = (state.visibleCount / zoomChange).roundToInt()
//    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFF182028)
            )
//            .transformable(state.transformableState)
    ) {

        val chartWidth = size.width - 128.dp.value
        val chartHeight = size.height - 64.dp.value

        val state = ScheduleViewModel(chartWidth, chartHeight)

//        state.width = chartWidth
//        state.height = chartHeight

        drawLine(
            color = Color.White,
            strokeWidth = 2.dp.value,
            start = Offset(0f, chartHeight),
            end = Offset(chartWidth, chartHeight)
        )

        drawLine(
            color = Color.White,
            strokeWidth = 2.dp.value,
            start = Offset(chartWidth, 0f),
            end = Offset(chartWidth, chartHeight)
        )

        for (i in state.startItem until state.endItem - 1) {

            val itItem = state.visiblePoints[i]
            val nextItem = state.visiblePoints[i + 1]

            println(
                "${state.xCoord(itItem.time)} ${state.yCoord(itItem.value)}\n${state.xCoord(nextItem.time)} ${state.yCoord(nextItem.value)}\n\n"
            )

            drawLine(
                color = Color.Green,
                strokeWidth = 10.dp.value,
                start = Offset(state.xCoord(itItem.time), state.yCoord(itItem.value)),
                end = Offset(state.xCoord(nextItem.time), state.yCoord(nextItem.value))
            )
        }

        state.priceLines.forEach { value: Float ->
            val yOffset = state.yCoord(value)
            drawLine(
                color = Color.White,
                strokeWidth = 1.dp.value,
                start = Offset(0f, yOffset),
                end = Offset(chartWidth, yOffset),
                pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(10f, 20f), phase = 5f)
            )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun SchedulePreview() {
    ScheduleScreen()
}