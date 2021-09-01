package com.example.turk_contacts.contact


import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.turk_contacts.api.ContactApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@SuppressLint("CheckResult")
@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactApi: ContactApi
) : ViewModel() {


    fun getAllContacts(): Flow<PagingData<ContactItem>> =
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true)
        ) {
            ContactPagingSource(contactApi)
        }.flow.cachedIn(viewModelScope)

}