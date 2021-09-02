package com.example.turk_contacts.contact


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.turk_contacts.api.ContactApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CheckResult")
@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {


    private val currentQuery = MutableLiveData("")

    val contacts = currentQuery.switchMap { queryString ->
        contactRepository.getAllContacts(queryString).cachedIn(viewModelScope)
    }

    fun searchContact(input: String) {
        currentQuery.postValue(input)
    }

}