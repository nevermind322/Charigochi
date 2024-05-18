package com.example.charigochi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface CatDAO {
    @Update
    suspend fun updateCat(cat: CatEntity)

    @Query("SELECT * FROM CATS where cats.id=:id")
    suspend fun getById(id : Int) : CatEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(cat: CatEntity)

    @Query("SELECT * FROM cats")
    suspend fun getAll(): List<CatEntity>

    @Upsert
    suspend fun upsertCats(vararg cats: CatEntity)
}