package com.example.turk_contacts.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val autoId: Int=0,
    @ColumnInfo(name = "id")
    val id: String
)