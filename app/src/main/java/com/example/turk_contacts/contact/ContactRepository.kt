package com.example.turk_contacts.contact

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.turk_contacts.api.ContactApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ContactRepository @Inject constructor(
    private val contactApi: ContactApi
) {

    fun getAllContacts(searchInput: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            )
        ) {
            ContactPagingSource(contactApi, searchInput)
        }.liveData

    suspend fun deleteContact(contactItem: ContactItem) {
        contactApi.deleteContact(contactItem.id)
    }
}