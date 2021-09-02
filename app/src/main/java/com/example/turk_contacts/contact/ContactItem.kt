package com.example.turk_contacts.contact

import android.os.Parcelable
import com.example.turk_contacts.util.ContactDate
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ContactItem(
    @field:Json(name = "email") val email: String,
    @field:Json(name = "company_name") val company_name: String,
    @field:Json(name = "department") val department: String,
    @field:Json(name = "number") val number: Int,
    @field:Json(name = "surname") val surname: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "createdAt") val createdAt: String,
    @field:Json(name = "id") val id: String,
) : Parcelable {
    val createdContactDate = ContactDate.convertToContactDate(createdAt)
}