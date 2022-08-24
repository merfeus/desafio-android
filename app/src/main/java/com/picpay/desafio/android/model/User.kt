package com.picpay.desafio.android.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int?,

    @ColumnInfo(name = "img_user")
    @SerializedName("img") val img: String?,

    @ColumnInfo(name = "name_user")
    @SerializedName("name") val name: String?,

    @ColumnInfo(name = "username_user")
    @SerializedName("username") val username: String?
)