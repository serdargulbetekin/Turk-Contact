package com.example.turk_contacts.api

import com.example.turk_contacts.contact.ContactItem
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ContactApi {
    @GET("contacts")
    suspend fun getContacts(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<ContactItem>

    @GET("contacts")
    suspend fun searchContacts(
        @Query("search") input: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<ContactItem>

    @PUT("contacts/{id}")
    suspend fun updateContact(
        @Path("id") id: String,
        @Query("name") name: String,
        @Query("surname") surname: String,
    )

    @DELETE("contacts/{id}")
    suspend fun deleteContact(
        @Path("id") id: String
    )

}