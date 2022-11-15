package com.masharo.pulser.presentation.ui.screen

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.masharo.pulser.presentation.model.Point
import kotlin.math.roundToInt

class TestVM(
    private val spaceY: Float,
    private val spaceX: Float
) {

    private val points = listOf(
        Point(
            value = 1f,
            time = 1f
        ),
        Point(
            value = 4f,
            time = 2f
        ),
        Point(
            value = 6f,
            time = 3f
        ),
        Point(
            value = 2f,
            time = 4f
        ),
        Point(
            value = 7f,
            time = 5f
        ),
        Point(
            value = 8f,
            time = 6f
        ),
        Point(
            value = 3f,
            time = 8f
        ),
        Point(
            value = 1f,
            time = 9f
        ),
        Point(
            value = 9f,
            time = 10f
        ),
        Point(
            value = 1f,
            time = 11f
        ),
        Point(
            value = 9f,
            time = 12f
        )
    )

    private var candleInGrid = Float.MAX_VALUE
    var timeLines by mutableStateOf(listOf<Point>())


    private var visibleCount by mutableStateOf(10)
    private var scrollOffset by mutableStateOf(0f)

    private val Float.scrolledPoints: Float
        get() = this * visibleCount.toFloat() / width

    private var width = 0f
    private var height = 0f
    private var linesHeight = 0f

    private val yMin by derivedStateOf {
        visiblePoints.minOfOrNull { it.value } ?: 0f
    }

    private val yMax by derivedStateOf {
        visiblePoints.maxOfOrNull { it.value } ?: 0f
    }

    private val xMin by derivedStateOf {
        visiblePoints.minOfOrNull { it.time } ?: 0f
    }

    private val xMax by derivedStateOf {
        visiblePoints.maxOfOrNull { it.time } ?: 0f
    }

    private val xParam by derivedStateOf {
        width / (xMax - xMin)
    }

    private val yParam by derivedStateOf {
        height / ( yMax - yMin)
    }

    val scrollableState = ScrollableState {
        if (visiblePoints.size < points.size) {
            scrollOffset = if (it > 0) {
                (scrollOffset - it.scrolledPoints).coerceAtLeast(0f)
            } else {
                (scrollOffset - it.scrolledPoints).coerceAtMost(points.lastIndex.toFloat())
            }
        }
        it
    }

    val visiblePoints by derivedStateOf {
        if (points.isNotEmpty()) {
            points.subList(
                scrollOffset.roundToInt().coerceAtLeast(0),
                (scrollOffset.roundToInt() + visibleCount).coerceAtMost(points.size)
            )
        } else {
            emptyList()
        }
    }

    fun setViewSize(width: Float, height: Float) {
        this.width = width - spaceX
        this.height = height - spaceY
        this.linesHeight = height
    }

    fun xCoord(x: Float): Float {
        return (x - xMin) * xParam
    }

    fun yCoord(y: Float): Float {
        return (y - yMin) * yParam + spaceY / 2
    }

    fun yCoordForLines(y: Float): Float {
        return (y - yMin) * yParam
    }

    val priceLines by derivedStateOf {
        val priceItem = (yMax - yMin) / 10
        mutableListOf<Float>().apply {
            repeat(10) { if (it > 0) add(yMax - priceItem * it) }
        }
    }

    fun calculateGridWidth() {
        val candleWidth = width / visibleCount
        val currentGridWidth = candleInGrid * candleWidth
        when {
            currentGridWidth < MIN_GRID_WIDTH -> {
                candleInGrid = MAX_GRID_WIDTH / candleWidth
                timeLines = points.filterIndexed { index, _ -> index % candleInGrid.roundToInt() == 0 }
            }
            currentGridWidth > MAX_GRID_WIDTH -> {
                candleInGrid = MIN_GRID_WIDTH / candleWidth
                timeLines = points.filterIndexed { index, _ -> index % candleInGrid.roundToInt() == 0 }
            }
        }
    }

    val transformableState = TransformableState { zoomChange, _, _ ->
        val visible = (visibleCount / zoomChange).roundToInt()
        visibleCount = if (visible > points.size) points.size else visible
    }

    fun xOffset(point: Point) = width * visiblePoints.indexOf(point).toFloat() / visibleCount.toFloat()

    companion object {
        private const val MAX_GRID_WIDTH = 500
        private const val MIN_GRID_WIDTH = 250
        private const val MAX_CANDLES = 100
        private const val MIN_CANDLES = 30
        private const val START_CANDLES = 60
        private const val PRICES_COUNT = 10
    }

}