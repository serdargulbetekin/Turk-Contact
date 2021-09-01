package com.example.turk_contacts.util

fun ContactDate.getUIDate(): String {
    val month = when (this.month) {
        1 -> {
            "Ocak"
        }
        2 -> {
            "Şubat"
        }
        3 -> {
            "Mart"
        }
        4 -> {
            "Nisan"
        }
        5 -> {
            "Mayıs"
        }
        6 -> {
            "Haziran"
        }
        7 -> {
            "Temmuz"
        }
        8 -> {
            "Ağustos"
        }
        9 -> {
            "Eylül"
        }
        10 -> {
            "Ekim"
        }
        11 -> {
            "Kasım"
        }
        12 -> {
            "Aralık"
        }
        else -> ""
    }
    return this.day.toString() + " " + month + " " + this.year.toString()
}