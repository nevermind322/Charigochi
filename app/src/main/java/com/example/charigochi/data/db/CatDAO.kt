package com.example.charigochi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CatDAO {
    @Update
    suspend fun updateCat(cat: CatEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCat(cat: CatEntity)

    @Query("SELECT * FROM cats")
    suspend fun getAll(): List<CatEntity>
}