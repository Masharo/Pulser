package com.masharo.pulser.data.database

import com.masharo.pulser.data.model.Coords
import kotlinx.coroutines.flow.Flow

interface PulserStorage{

    suspend fun putCoords(vararg coords: Coords)

    fun getCoords(): Flow<List<Coords>>

}