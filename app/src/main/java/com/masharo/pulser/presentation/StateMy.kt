package com.masharo.pulser.presentation

import androidx.compose.runtime.*
import com.masharo.pulser.presentation.model.Point


class StateMy {

        val points = listOf(
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
            )
        )

        var visiblePointCount by mutableStateOf(6)

        private var viewWidth = 0f
        private var viewHeight = 0f

        private val visiblePoints by derivedStateOf {
            if (points.isNotEmpty() && points.size > visiblePointCount) {
                points.subList(
                    0,
                    visiblePointCount
                )
            } else {
                points
            }
        }

        fun setViewSize(width: Float, height: Float) {
            viewWidth = width
            viewHeight = height
        }

        fun xOffset(point: Point): Float  {
            val maxX = visiblePoints.maxOf {
                it.time
            }

            val min = visiblePoints.minOf {
                it.time
            }

            val go = viewWidth / (maxX - min)

            return (point.time - min) * go
        }

        fun yOffset(point: Point): Float {
            val max = visiblePoints.maxOf {
                it.value
            }

            val min = visiblePoints.minOf {
                it.value
            }

            val go = viewHeight / (max - min)

            return (point.value - min) * go
        }

}


//class StateMy {
//
//    private var points = listOf(
//        Point(
//            value = 1f,
//            time = 1f
//        ),
//        Point(
//            value = 4f,
//            time = 2f
//        ),
//        Point(
//            value = 6f,
//            time = 3f
//        ),
//        Point(
//            value = 2f,
//            time = 4f
//        ),
//        Point(
//            value = 7f,
//            time = 5f
//        ),
//        Point(
//            value = 8f,
//            time = 6f
//        ),
//        Point(
//            value = 5f,
//            time = 7f
//        )
//    )
//
//    private var visiblePointCount by mutableStateOf(60)
//
//    private var viewWidth = 0f
//    private var viewHeight = 0f
//
//    val visiblePoints by derivedStateOf {
//        if (points.isNotEmpty() && points.size > visiblePointCount) {
//            points.subList(
//                0,
//                visiblePointCount
//            )
//        } else {
//            points
//        }
//    }
//
//    private val xConstScale by derivedStateOf {
//        viewHeight / (visiblePoints.maxOf { it.time } - minX)
//    }
//
//    private val minY by derivedStateOf {
//        visiblePoints.minOf { it.value }
//    }
//
//    private val minX by derivedStateOf {
//        visiblePoints.minOf { it.time }
//    }
//
//    private val yConstScale by derivedStateOf {
//        viewHeight / (visiblePoints.maxOf { it.value } - minY)
//    }
//
//    fun setViewSize(width: Float, height: Float) {
//        viewWidth = width
//        viewHeight = height
//    }
//
//    fun xOffset(point: Point): Float  {
//        return (point.time - minX) * xConstScale
//    }
//
//    fun yOffset(point: Point): Float {
//        return (point.time - minY) * yConstScale
//    }
//
//}