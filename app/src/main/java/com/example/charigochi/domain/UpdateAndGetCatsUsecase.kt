package com.example.charigochi.domain

import android.util.Log
import com.example.charigochi.data.CatRepo
import com.example.charigochi.data.db.CATS_INIT
import com.example.charigochi.data.db.CatEntity
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UpdateAndGetCatsUsecase @Inject constructor(private val catRepo: CatRepo) {

    suspend operator fun invoke(): List<CatEntity> {
        val cats = catRepo.getAll()
        Log.d("CATS", cats.toString())
        val newCats = mutableListOf<CatEntity>()
        val date = Date()
        if (cats.isEmpty()) {
            for (cat in CATS_INIT) {
                newCats.add(
                    if (!cat.unlocked) cat else cat.copy(
                        lastBellyfulUpdate = date,
                        happinessLastUpdate = date
                    )
                )
            }
        } else for (cat in cats) {
            if (cat.unlocked) {
                val ms = date.time - cat.lastBellyfulUpdate!!.time
                val hours = TimeUnit.HOURS.convert(ms, TimeUnit.MILLISECONDS).toInt()
                val bellyful = (cat.bellyful - 3 * hours).let { if (it >= 0) it else 0 }
                newCats.add(cat.copy(bellyful = bellyful, lastBellyfulUpdate = date))
            } else
                newCats.add(cat)
        }

        catRepo.upsertCat(*newCats.toTypedArray())
        return newCats
    }
}