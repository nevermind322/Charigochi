package com.example.charigochi.data.db
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "cats")
data class CatEntity (
    @PrimaryKey val id : Int,
    val bellyful : Int,
    val lastBellyfulUpdate : Date,
    val happiness : Int,
    val happinessLastUpdate : Date
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