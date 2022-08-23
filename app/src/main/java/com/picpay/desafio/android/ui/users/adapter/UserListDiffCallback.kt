package com.picpay.desafio.android.ui.users.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.model.User

class UserListDiffCallback() : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}