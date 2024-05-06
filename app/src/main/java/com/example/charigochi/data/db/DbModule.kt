package com.example.charigochi.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun getDb( @ApplicationContext context: Context) : CharigochiDb {
        return Room.databaseBuilder(context, CharigochiDb::class.java, "db").build()
    }

    @Provides
    fun getSearchDao(db: CharigochiDb) = db.catDAO()

}