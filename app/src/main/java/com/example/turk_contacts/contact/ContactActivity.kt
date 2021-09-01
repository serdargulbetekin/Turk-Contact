package com.example.turk_contacts.contact

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

        viewBinding.progressBar.isVisible = true
        viewBinding.recyclerView.apply {
            isVisible = false
            layoutManager = LinearLayoutManager(this@ContactActivity)
            adapter = contactAdapter.withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter { contactAdapter.retry() },
                footer = LoadingStateAdapter { contactAdapter.retry() }
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getAllContacts().collectLatest { response ->
                viewBinding.apply {
                    recyclerView.isVisible = true
                    progressBar.isVisible = false
                }

                contactAdapter.submitData(response)

            }
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