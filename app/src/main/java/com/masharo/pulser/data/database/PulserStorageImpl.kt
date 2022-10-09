package com.masharo.pulser.data.database

import com.masharo.pulser.data.model.Coords
import kotlinx.coroutines.flow.Flow

class PulserStorageImpl(
    private val db: PulserDatabase
): PulserStorage {

    override suspend fun putCoords(vararg coords: Coords) {
        db.getPulserDao().insertCoords(*coords)
    }

    override fun getCoords(): Flow<List<Coords>> {
        return db.getPulserDao().getCoords()
    }

}