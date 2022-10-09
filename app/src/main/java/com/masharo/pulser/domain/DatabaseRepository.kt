package com.masharo.pulser.domain

import com.masharo.pulser.data.model.Coords
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun getCoords(): Flow<List<Coords>>

    suspend fun putCoords(vararg coords: Coords)

}