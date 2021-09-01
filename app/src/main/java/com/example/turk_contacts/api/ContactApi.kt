package com.example.turk_contacts.api

import com.example.turk_contacts.contact.ContactItem
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactApi {
    @GET("contacts")
    suspend fun getContacts(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<ContactItem>
}