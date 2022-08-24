package com.picpay.desafio.android.ui.users.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.model.User

class UserListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ListItemUserBinding = ListItemUserBinding.bind(itemView)

    fun bind(user: User) {
        binding.name.text = user.name
        binding.username.text = user.username
        binding.progressBar.visibility = View.VISIBLE
        Glide.with(itemView.context)
            .load(user.img)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.isVisible = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.isVisible = false
                    return false
                }

            })
            .into(binding.picture)
    }
}