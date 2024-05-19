package com.example.charigochi.data

import com.example.charigochi.data.network.CatFactService
import com.example.charigochi.data.network.service
import com.example.charigochi.model.Language
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatFactRepository @Inject constructor() {
    private val catFactService: CatFactService = service
    suspend fun getFact(language: Language) = withContext(Dispatchers.IO) {
        try {
            catFactService.getFacts(language.code).data[0]
        } catch (e: Exception) {
            language.default
        }
    }
}

