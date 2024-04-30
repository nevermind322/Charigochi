package com.example.charigochi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface CatDAO {
    @Update
    fun updateCat(cat: CatEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCat(cat: CatEntity)
}