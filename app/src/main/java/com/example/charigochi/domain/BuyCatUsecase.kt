package com.example.charigochi.domain

import com.example.charigochi.data.CatRepo
import com.example.charigochi.data.ProgressRepository
import com.example.charigochi.data.db.price
import kotlinx.coroutines.flow.first
import java.util.Date
import javax.inject.Inject

class BuyCatUsecase @Inject constructor(
    private val catRepo: CatRepo,
    private val progressRepository: ProgressRepository
) {

    suspend fun buyCat(catId: Int) {
        val cat = catRepo.getById(catId)
        val now = Date()
        catRepo.upsertCat(
            cat.copy(
                unlocked = true,
                lastBellyfulUpdate = now,
                happinessLastUpdate = now,
                bellyful = 100,
                happiness = 100
            )
        )
        progressRepository.spendMoney(cat.price)
    }

}