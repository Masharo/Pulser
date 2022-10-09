package com.masharo.pulser.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coords(

    @PrimaryKey(autoGenerate = true)
    val _id: Long,
    val x: Float,
    val y: Float

)