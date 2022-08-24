package com.picpay.desafio.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.database.dao.UsersDAO
import com.picpay.desafio.android.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val usersDAO: UsersDAO

}