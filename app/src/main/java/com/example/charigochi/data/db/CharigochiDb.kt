package com.example.charigochi.data.db

import androidx.room.Database
import androidx.room.TypeConverters

@Database(entities = [CatEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharigochiDb {
    abstract fun catDAO() : CatDAO
}