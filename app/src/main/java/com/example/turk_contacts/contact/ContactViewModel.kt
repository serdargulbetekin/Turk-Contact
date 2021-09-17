package com.example.turk_contacts.contact


import android.annotation.SuppressLint
import androidx.lifecycle.*
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CheckResult")
@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {


    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val contacts = currentQuery.switchMap { queryString ->
        contactRepository.getAllContacts(queryString).cachedIn(viewModelScope)
    }

    fun searchContact(input: String) {
        currentQuery.postValue(input)
    }

    fun onUpdate(contactItem: ContactItem) {

    }

    fun onDelete(contactItem: ContactItem) {
        _progress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.deleteContact(contactItem)
            searchContact(DEFAULT_QUERY)
            _progress.postValue(false)
        }
    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }
}

enum class MenuItemEnum(val typeName: String) {
    UPDATE("Güncelle"),
    DELETE("Sil"),
    DETAIL("Detay")
}