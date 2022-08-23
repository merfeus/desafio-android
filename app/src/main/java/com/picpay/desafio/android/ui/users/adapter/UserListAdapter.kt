package com.picpay.desafio.android.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User

class UserListAdapter : ListAdapter<User, RecyclerView.ViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false).let {
            return UserListItemViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as UserListItemViewHolder
        getItem(position).let {
            holder.bind(it)
        }
    }
}