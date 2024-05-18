package com.example.charigochi.data.db

import android.content.Context
import androidx.room.Room
import com.example.charigochi.utils.progressDataStore
import com.example.charigochi.utils.settingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProgressDataStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SettingsDataStore

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

    @ProgressDataStore
    @Provides
    fun getProgressDataStore(@ApplicationContext context: Context) = context.progressDataStore


    @SettingsDataStore
    @Provides
    fun getSettingsDataStore(@ApplicationContext context: Context) = context.settingsDataStore
}