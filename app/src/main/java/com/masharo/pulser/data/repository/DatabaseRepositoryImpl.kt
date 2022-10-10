package com.masharo.pulser.data.repository

import com.masharo.pulser.data.database.PulserStorage
import com.masharo.pulser.data.model.Coords
import com.masharo.pulser.domain.DatabaseRepository
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(
    private val storege: PulserStorage
): DatabaseRepository {

    override fun getCoords(): Flow<List<Coords>> {
        return storege.getCoords()
    }

    override suspend fun putCoords(vararg coords: Coords) {
        storege.putCoords(*coords)
    }

}