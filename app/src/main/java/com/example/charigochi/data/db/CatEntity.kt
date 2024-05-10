package com.example.charigochi.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val bellyful: Int,
    val lastBellyfulUpdate: Date?,
    val happiness: Int,
    val happinessLastUpdate: Date?,
    val unlocked: Boolean,
    val deaths: Int
)

val CatEntity.imageName: String
    get() = "cat$id.jpg"

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

val CATS_INIT = listOf(
    CatEntity(0, "Гав", 100, null, 100, null, true, 0),
    CatEntity(1, "Кот в сапогах", 100, null, 100, null, false, 0),
    CatEntity(2, "Леопольд", 100, null, 100, null, false, 0),
    CatEntity(3, "Василий", 100, null, 100, null, false, 0),
    CatEntity(4, "Мэри", 100, null, 100, null, false, 0),
    CatEntity(5, "Сильвестр", 100, null, 100, null, false, 0)
)
