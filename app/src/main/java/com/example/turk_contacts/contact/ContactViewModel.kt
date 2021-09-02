package com.example.turk_contacts.contact


import android.annotation.SuppressLint
import androidx.lifecycle.*
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

    private val menuItemMutableLiveData = MutableLiveData<List<MenuItemEnum>>()
    val menuItemLiveData: LiveData<List<MenuItemEnum>>
        get() = menuItemMutableLiveData

    private val currentQuery = MutableLiveData("")

    val contacts = currentQuery.switchMap { queryString ->
        contactRepository.getAllContacts(queryString).cachedIn(viewModelScope)
    }

    fun searchContact(input: String) {
        currentQuery.postValue(input)
    }

    fun on3dotClick() {
        menuItemMutableLiveData.postValue(MenuItemEnum.values().toList())
    }

    fun onUpdate(contactItem: ContactItem) {

    }

    fun onDelete(contactItem: ContactItem) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.deleteContact(contactItem)
        }
    }

}

enum class MenuItemEnum(val typeName: String) {
    UPDATE("Güncelle"),
    DELETE("Sil"),
    DETAIL("Detay")
}