package com.masharo.pulser.presentation.vm

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.masharo.pulser.presentation.model.Point
import java.util.*

class ScheduleViewModel {

    private val visibleCount = mutableStateOf(10)
    val startItem = mutableStateOf(0)
    val width = mutableStateOf(0f)
    val height = mutableStateOf(0f)
    val stateId = mutableStateOf(UUID.randomUUID().toString())

    private fun updateState() {
        stateId.value = UUID.randomUUID().toString()
    }

    fun setWidth(width: Float) {
        this.width.value = width
        updateState()
    }

    fun setHeight(height: Float) {
        this.height.value = height
        updateState()
    }

    fun setVisibleCount(value: Int) {
        visibleCount.value = value
        endItemCalculate()
        updateLocalList()
        maxUpdate()
        updateState()
    }

    fun setStartItem(value: Int) {
        startItem.value = value
        endItemCalculate()
        updateLocalList()
        maxUpdate()
        updateState()
    }

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
    var endItem = startItem.value + visibleCount.value
        private set(value) { field = value }
    var visiblePoints = points.subList(startItem.value, endItem)
        private set(value) { field = value }

    private var yMin = 0f
    private var xMin = 0f
    private var yMax = 0f
    private var xMax = 0f
    private var yParam = 0f
    private var xParam = 0f

    init {
        maxUpdate()
        endItemCalculate()
        updateLocalList()
    }

    fun maxUpdate() {
        yMin = visiblePoints.minOf {
            it.value
        }

        yMax = visiblePoints.maxOf {
            it.value
        }

        yParam = height.value / ( yMax - yMin)

        xMin = visiblePoints.minOf {
            it.time
        }

        xMax = visiblePoints.maxOf {
            it.time
        }

        xParam = width.value / ( xMax - xMin)
    }

    fun endItemCalculate() {
        endItem =
            (startItem.value + visibleCount.value).let {
                if (it >= points.size) {
                    points.size
                } else {
                    it
                }
        }
    }

    fun updateLocalList() {
        points.subList(
            startItem.value,
            endItem
        )
    }

    fun xCoord(x: Float): Float {
        return (x - xMin) * xParam
    }

    fun yCoord(y: Float): Float {
        return (y - yMin) * yParam
    }

    val priceLines by derivedStateOf {
        val priceItem = (xMax - xMin) / 10
        mutableListOf<Float>().apply {
            repeat(10) { if (it > 0) add(xMax - priceItem * it) }
        }
    }

    val transformableState = TransformableState { zoomChange, _, _ ->
        endItemCalculate()
        updateLocalList()
        maxUpdate()
    }
}