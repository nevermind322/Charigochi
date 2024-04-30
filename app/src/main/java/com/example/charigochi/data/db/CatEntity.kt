package com.example.charigochi.data.db
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "cats")
class CatEntity (
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "hunger") val hunger : Int,
    val lastHungerUpdate : Date
    )

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}