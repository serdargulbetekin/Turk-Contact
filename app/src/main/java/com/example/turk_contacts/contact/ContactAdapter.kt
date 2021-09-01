package com.example.turk_contacts.contact

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.turk_contacts.databinding.RowContactBinding

class ContactAdapter(private val onItemClick: (ContactItem) -> Unit) :
    PagingDataAdapter<ContactItem, ContactViewHolder>(Diff) {
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contactItem: ContactItem? = getItem(position)
        if (contactItem != null) {
            holder.bindItem(contactItem, onItemClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactViewHolder(
            RowContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    object Diff : DiffUtil.ItemCallback<ContactItem>() {
        override fun areItemsTheSame(oldItem: ContactItem, newItem: ContactItem) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ContactItem, newItem: ContactItem) =
            oldItem == newItem
    }
}

class ContactViewHolder(private val contactBinding: RowContactBinding) :
    RecyclerView.ViewHolder(contactBinding.root) {

    @SuppressLint("SetTextI18n")
    fun bindItem(contactItem: ContactItem, onItemClick: (ContactItem) -> Unit) {
        contactBinding.apply {
            textViewNameSurname.text = contactItem.name + " " + contactItem.surname
            textViewEmail.text = contactItem.email
            textViewCompanyName.text = contactItem.company_name
            textViewDepartment.text = contactItem.department

            constraintContainer.setOnClickListener {
                onItemClick(contactItem)
            }
        }
    }

}