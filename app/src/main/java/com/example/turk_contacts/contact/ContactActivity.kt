package com.example.turk_contacts.contact

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turk_contacts.contactdetail.ContactDetailActivity
import com.example.turk_contacts.databinding.ActivityContactBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private val viewBinding by lazy { ActivityContactBinding.inflate(layoutInflater) }
    private val viewModel: ContactViewModel by viewModels()
    private val contactAdapter by lazy {
        ContactAdapter {
            onItemClick(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.apply {
            progressBar.isVisible = true
            recyclerView.apply {
                isVisible = false
                layoutManager = LinearLayoutManager(this@ContactActivity)
                adapter = contactAdapter.withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter { contactAdapter.retry() },
                    footer = LoadingStateAdapter { contactAdapter.retry() }
                )
            }
            contactToolbar.show(
                title = "Turk-Contact",
                showMenu = {
                    Toast.makeText(
                        this@ContactActivity,
                        "Menu will open in future",
                        Toast.LENGTH_SHORT
                    ).show()
                },
            )

            editTextSearch.addTextChangedListener {
                viewModel.searchContact(it?.toString() ?: "")
            }

        }

        viewModel.contacts.observe(this) { response ->
            viewBinding.apply {
                recyclerView.isVisible = true
                progressBar.isVisible = false
            }
            contactAdapter.submitData(this.lifecycle, response)
        }


    }

    private fun onItemClick(contactItem: ContactItem) {
        startActivity(
            ContactDetailActivity.createIntent(
                this,
                contactItem
            )
        )
    }
}