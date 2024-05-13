package com.example.charigochi.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.charigochi.R
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


// TODO: поменять кошьку на картинки, когда они будут
val CatEntity.imageRes: Int
    get() = when (id) {
        0 -> R.drawable.koshka
        1 -> R.drawable.koshka
        2 -> R.drawable.leopold
        3 -> R.drawable.koshka
        4 -> R.drawable.koshka
        5 -> R.drawable.silvestr
        else -> R.drawable.koshka
    }

val CatEntity.price : Int
    get() = when(id) {
        0 -> 500
        1 -> 600
        2 -> 700
        3 -> 800
        4 -> 900
        5 -> 1000
        else -> 0
    }

val CATS_INIT = listOf(
    CatEntity(0, "Гав", 100, null, 100, null, true, 0),
    CatEntity(1, "Кот в сапогах", 100, null, 100, null, false, 0),
    CatEntity(2, "Леопольд", 100, null, 100, null, false, 0),
    CatEntity(3, "Василий", 100, null, 100, null, false, 0),
    CatEntity(4, "Мэри", 100, null, 100, null, false, 0),
    CatEntity(5, "Сильвестр", 100, null, 100, null, false, 0)
)
