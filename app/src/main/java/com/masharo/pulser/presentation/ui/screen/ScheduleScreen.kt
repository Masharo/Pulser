package com.masharo.pulser.presentation.ui.screen

import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masharo.pulser.presentation.vm.ScheduleViewModel
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleScreen() {

//    val transformableState = TransformableState { zoomChange, _, _ ->
//        state.visibleCount = (state.visibleCount / zoomChange).roundToInt()
//    }

    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        textSize = 35.sp.value
        color = Color.White.toArgb()
    }

    val bounds = Rect()
    val timeFormatter = DateTimeFormatter.ofPattern("dd.MM, HH:mm")
    val decimalFormat = DecimalFormat("##.00")

    val vm = TestVM(150f, 10f)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFF182028)
            )
            .scrollable(vm.scrollableState, Orientation.Horizontal)
            .transformable(vm.transformableState)
    ) {

        val chartWidth = size.width - 128.dp.value
        val chartHeight = size.height - 64.dp.value

        vm.setViewSize(chartWidth, chartHeight)
        vm.calculateGridWidth()

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

        for (i in 0 until vm.visiblePoints.size - 1) {

            val itItem = vm.visiblePoints[i]
            val nextItem = vm.visiblePoints[i + 1]

//            println(
//                "${vm.xCoord(itItem.time)} ${vm.yCoord(itItem.value)}\n${vm.xCoord(nextItem.time)} ${vm.yCoord(nextItem.value)}\n\n"
//            )

            drawLine(
                color = Color.Green,
                strokeWidth = 10.dp.value,
                start = Offset(vm.xCoord(itItem.time), vm.yCoord(itItem.value)),
                end = Offset(vm.xCoord(nextItem.time), vm.yCoord(nextItem.value))
            )
        }

        vm.priceLines.forEach { value: Float ->
            val yOffset = vm.yCoord(value)
            val text = decimalFormat.format(value)
            drawLine(
                color = Color.White,
                strokeWidth = 1.dp.value,
                start = Offset(0f, yOffset),
                end = Offset(chartWidth, yOffset),
                pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(10f, 20f), phase = 5f)
            )
            drawIntoCanvas {
                textPaint.getTextBounds(text, 0, text.length, bounds)
                val textHeight = bounds.height()
                it.nativeCanvas.drawText(
                    text,
                    chartWidth + 8.dp.value,
                    yOffset + textHeight / 2,
                    textPaint
                )
            }
        }

        vm.timeLines.forEach { point ->
            val offset = vm.xCoord(point.time)
            if (offset !in 0f..chartWidth) return@forEach
            drawLine(
                color = Color.White,
                strokeWidth = 1.dp.value,
                start = Offset(offset, 0f),
                end = Offset(offset, chartHeight),
                pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(10f, 20f), phase = 5f)
            )
            drawIntoCanvas {
                val text = point.time.toString()
                textPaint.getTextBounds(text, 0, text.length, bounds)
                val textHeight = bounds.height()
                val textWidth = bounds.width()
                it.nativeCanvas.drawText(
                    text,
                    offset - textWidth / 2,
                    chartHeight + 8.dp.value + textHeight,
                    textPaint
                )
            }
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