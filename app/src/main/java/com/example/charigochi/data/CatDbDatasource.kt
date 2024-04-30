package com.example.charigochi.data

import com.example.charigochi.data.db.CatDAO
import com.example.charigochi.data.db.CatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatDbDatasource(private val catDAO: CatDAO) {

    suspend fun getAll() = withContext(Dispatchers.IO) { catDAO.getAll() }

    suspend fun updateCat(catEntity: CatEntity) =
        withContext(Dispatchers.IO) { catDAO.updateCat(catEntity) }

    suspend fun insertCat(catEntity: CatEntity) =
        withContext(Dispatchers.IO) { catDAO.insertCat(catEntity) }

}