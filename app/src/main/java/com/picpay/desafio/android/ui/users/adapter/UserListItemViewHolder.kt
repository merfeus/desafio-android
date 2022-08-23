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
import com.picpay.desafio.android.model.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        itemView.name.text = user.name
        itemView.username.text = user.username
        itemView.progressBar.visibility = View.VISIBLE
        Glide.with(itemView.context)
            .load(user.img)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemView.progressBar.isVisible = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemView.progressBar.isVisible = false
                    return false
                }

            })
            .into(itemView.picture)
    }
}