package com.example.charigochi.data

import com.example.charigochi.data.db.CatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatRepo(private val catDbDatasource: CatDbDatasource  ) {

    suspend fun getAll() = catDbDatasource.getAll()

    suspend fun updateCat(catEntity: CatEntity) =
        catDbDatasource.updateCat(catEntity)

    suspend fun insertCat(catEntity: CatEntity) =
        catDbDatasource.insertCat(catEntity)

}