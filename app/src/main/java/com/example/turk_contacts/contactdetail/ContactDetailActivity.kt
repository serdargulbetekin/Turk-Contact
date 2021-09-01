package com.example.turk_contacts.contactdetail


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.turk_contacts.contact.ContactItem
import com.example.turk_contacts.databinding.ActivityContactDetailBinding
import com.example.turk_contacts.util.getUIDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailActivity : AppCompatActivity() {

    private val viewBinding by lazy { ActivityContactDetailBinding.inflate(layoutInflater) }
    private val viewModel: ContactDetailViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel.contactItem.observe(this, {
            viewBinding.apply {
                textViewNameSurname.text = it.name + " " + it.surname
                textViewEmail.text = it.email
                textViewCompanyName.text = it.company_name
                textViewDepartment.text = it.department
                textViewCreatedAt.text = it.createdContactDate.getUIDate()

                contactToolbar.show(
                    title = it.name + " " + it.surname,
                    showBack = { onBackPressed() },
                    showRightIcon = {
                        viewModel.onRightIconClick(it)
                    }
                )
            }
        })

    }

    companion object {
        const val EXTRAS_CONTACT = "EXTRAS_CONTACT"

        fun createIntent(
            context: Context,
            contactItem: ContactItem
        ) =
            Intent(context, ContactDetailActivity::class.java).apply {
                putExtra(EXTRAS_CONTACT, contactItem)
            }
    }
}