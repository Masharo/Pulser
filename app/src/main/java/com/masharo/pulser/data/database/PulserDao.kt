package com.masharo.pulser.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.masharo.pulser.data.model.Coords
import kotlinx.coroutines.flow.Flow

@Dao
interface PulserDao {

    @Insert
    suspend fun insertCoords(vararg coords: Coords)

    @Query("SELECT * FROM Coords")
    fun getCoords(): Flow<List<Coords>>

}