package com.example.charigochi.data

import com.example.charigochi.data.db.CatDAO
import com.example.charigochi.data.db.CatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatDbDatasource @Inject constructor(private val catDAO: CatDAO) {


    val allFlow = catDAO.allFlow()

    suspend fun getById(id: Int) = withContext(Dispatchers.IO) { catDAO.getById(id) }

    suspend fun getAll() = withContext(Dispatchers.IO) { catDAO.getAll() }

    suspend fun updateCat(catEntity: CatEntity) =
        withContext(Dispatchers.IO) { catDAO.updateCat(catEntity) }

    suspend fun insertCat(catEntity: CatEntity) =
        withContext(Dispatchers.IO) { catDAO.insertCat(catEntity) }

    suspend fun upsertCats(vararg cats: CatEntity) =
        withContext(Dispatchers.IO) { catDAO.upsertCats(*cats) }

}