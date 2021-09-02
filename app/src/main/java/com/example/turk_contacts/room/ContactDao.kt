package com.example.turk_contacts.room

import androidx.room.*

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun getAllContact(): List<ContactEntity>

    @Insert
    fun insertContact(contactEntity: ContactEntity)

    @Delete
    fun deleteContact(contactEntity: ContactEntity)

    @Update
    fun updateTodo(vararg contactEntity: ContactEntity)
}