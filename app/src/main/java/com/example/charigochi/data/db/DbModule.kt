package com.example.charigochi.data.db

import android.content.Context
import androidx.room.Room
import com.example.charigochi.utils.progressDataStore
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
    fun getDb(@ApplicationContext context: Context): CharigochiDb {
        return Room.databaseBuilder(context, CharigochiDb::class.java, "cats.db")
            //.createFromAsset("cats.db")
            .build()
    }

    @Provides
    fun getSearchDao(db: CharigochiDb) = db.catDAO()

    @Provides
    fun getDataStore(@ApplicationContext context: Context) = context.progressDataStore

}