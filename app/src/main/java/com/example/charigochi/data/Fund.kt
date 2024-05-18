package com.example.charigochi.data

data class Fund(val name: String, val url: String) {
}


val funds = listOf(
    Fund("Рука помощи РНД", "https://ruka-pomoshchi-rnd.ru/"),
    Fund("Дарящие надежду", "https://ghope.ru"),
    Fund("Добро Вместе", "https://dobrovmeste.ru/"),
    Fund("Котодетки", "https://www.kotodetki.ru/"),
    Fund("Подбери друга", "https://adoptapet.ru/"),
    Fund("Рэй", "https://rayfund.ru/")
)