package com.example.turk_contacts.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.turk_contacts.databinding.RowLoadingStateBinding

class LoadingStateAdapter(private val onRetry: () -> Unit) :
    LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bindItem(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadStateViewHolder(
            RowLoadingStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onRetry
        )
}

class LoadStateViewHolder(
    private val loadingStateBinding: RowLoadingStateBinding,
    onRetry: () -> Unit
) : RecyclerView.ViewHolder(loadingStateBinding.root) {


    fun bindItem(loadState: LoadState) {
        loadingStateBinding.apply {
            progressBar.isVisible = loadState is LoadState.Loading
            buttonRetry.isVisible = loadState !is LoadState.Loading
            textViewError.isVisible = loadState !is LoadState.Loading

            if (loadState is LoadState.Error) {
                textViewError.text = loadState.error.localizedMessage
            }
        }
    }

    init {
        loadingStateBinding.buttonRetry.setOnClickListener {
            onRetry.invoke()
        }

    }
}