package com.example.turk_contacts.uikit


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.turk_contacts.R
import com.example.turk_contacts.databinding.LayoutContactToolbarBinding

class ContactToolbar @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleArr: Int = 0
) : FrameLayout(context, attributes, defStyleArr) {
    private val binding by lazy {
        LayoutContactToolbarBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }


    fun show(
        title: CharSequence,
        showBack: (() -> Unit)? = null,
        showMenu: (() -> Unit)? = null,
        showRightIcon: (() -> Unit)? = null,
        rightIcon: Int = R.drawable.icon_edit
    ) {
        binding.apply {
            textViewTitle.text = title

            if (showBack != null) {
                imageViewBack.visibility = View.VISIBLE
                imageViewBack.setOnClickListener { showBack() }
            }
            if (showMenu != null) {
                imageViewMenu.visibility = View.VISIBLE
                imageViewMenu.setOnClickListener { showMenu() }
            }
            if (showRightIcon != null) {
                imageViewRight.setImageResource(rightIcon)
                imageViewRight.visibility = View.VISIBLE
                imageViewRight.setOnClickListener { showRightIcon() }
            }
        }
    }

}