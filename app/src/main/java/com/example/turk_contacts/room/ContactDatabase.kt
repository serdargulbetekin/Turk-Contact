package com.example.turk_contacts.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.turk_contacts.contact.ContactItem


@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}