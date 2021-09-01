package com.example.turk_contacts.util


data class ContactDate(
    val day: Int,
    val month: Int,
    val year: Int,
    val hour: String,
    val minute: String
) {
    override fun toString(): String {
        return "$day.$month.$year"
    }

    companion object {

        fun convertToContactDate(input: String): ContactDate {
            val dateAndHourList = input.split("T")
            val date = dateAndHourList[0].split("-")
            val hour = dateAndHourList[1].split(":")
            return if (date.size == 3) {
                if (hour.size == 3) {
                    ContactDate(
                        date[2].toInt(),
                        date[1].toInt(),
                        date[0].toInt(),
                        hour[0],
                        hour[1],
                    )
                } else {
                    ContactDate(
                        date[2].toInt(),
                        date[1].toInt(),
                        date[0].toInt(),
                        "00",
                        "00"
                    )
                }

            } else {
                ContactDate(0, 0, 0, "00", "00")
            }
        }
    }
}