package com.example.turk_contacts.contactdetail


import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.turk_contacts.contact.ContactItem
import com.example.turk_contacts.contactdetail.ContactDetailActivity.Companion.EXTRAS_CONTACT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : ViewModel() {


    private val _contactItem =
        savedStateHandle.getLiveData<ContactItem>(EXTRAS_CONTACT)

    val contactItem: LiveData<ContactItem>
        get() = _contactItem

    fun onRightIconClick(it: ContactItem) {

    }

}