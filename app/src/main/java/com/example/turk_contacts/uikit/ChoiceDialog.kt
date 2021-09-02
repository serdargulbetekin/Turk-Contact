package com.example.turk_contacts.uikit

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import com.example.turk_contacts.R
import com.example.turk_contacts.databinding.DialogChoiceBinding
import com.example.turk_contacts.databinding.RowChoiceBinding
import com.example.turk_contacts.util.dp
import com.example.turk_contacts.util.px


class ChoiceDialog<T>(
    context: Context,
    private val properties: ChoiceDialogProperties<T>
) : Dialog(context) {

    private val viewBinding by lazy { DialogChoiceBinding.inflate(layoutInflater) }

    private val adapter by lazy {
        ChoiceDialogAdapter(
            this,
            properties.itemList,
            properties.textProvider,
            properties.itemsOnClick
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        setContentView(viewBinding.root)
        viewBinding.recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        viewBinding.recyclerView.adapter = adapter
        viewBinding.textViewTitle.text = properties.title
        viewBinding.recyclerView.layoutParams.height =
            74.px * properties.itemList.size

    }


    override fun show() {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        super.show()
    }
}

class ChoiceDialogProperties<T> {
    var title: String? = null
    var itemList: List<T> = mutableListOf()
    var textProvider: (item: T) -> String = { it.toString() }
    var itemsOnClick: ((dialog: Dialog, item: T) -> Unit)? = null
}

class ChoiceDialogAdapter<T>(
    private val dialog: ChoiceDialog<T>,
    private val items: List<T>,
    private val textProvider: (T) -> String,
    private val onItemClick: ((dialog: Dialog, item: T) -> Unit)?
) : RecyclerView.Adapter<ChoiceDialogRowViewHolder<T>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChoiceDialogRowViewHolder<T> {
        return ChoiceDialogRowViewHolder(
            parent,
            textProvider,
            {
                onItemClick?.invoke(dialog, it)
            })
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
        holder: ChoiceDialogRowViewHolder<T>,
        position: Int
    ) {
        holder.bindItem(items[position])
    }

}

class ChoiceDialogRowViewHolder<T>(
    parent: ViewGroup,
    private val textProvider: (T) -> String,
    private val onItemClick: (T) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.row_choice, parent, false)
) {
    private val choiceBinding by lazy {
        RowChoiceBinding.bind(itemView.rootView)
    }

    fun bindItem(item: T) {
        choiceBinding.apply {
            textView.text = textProvider(item)
            textView.setOnClickListener { onItemClick(item) }
        }
    }
}