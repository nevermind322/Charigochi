package com.example.charigochi.data

import com.example.charigochi.data.db.CatEntity
import javax.inject.Inject

class CatRepo @Inject constructor(private val catDbDatasource: CatDbDatasource) {

    suspend fun getAll() = catDbDatasource.getAll()

    val allFlow = catDbDatasource.allFlow

    suspend fun getById(id: Int) = catDbDatasource.getById(id)

    suspend fun updateCat(catEntity: CatEntity) =
        catDbDatasource.updateCat(catEntity)

    suspend fun insertCat(catEntity: CatEntity) =
        catDbDatasource.insertCat(catEntity)

    suspend fun upsertCat(vararg cats: CatEntity) =
        catDbDatasource.upsertCats(*cats)

}