package com.picpay.desafio.android.repository

import com.picpay.desafio.android.database.dao.UsersDAO
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.NetworkResponse
import com.picpay.desafio.android.service.PicPayService

class RepositoryImpl(private val api: PicPayService, private val usersDAO: UsersDAO) : Repository {

    override suspend fun getUsers(): NetworkResponse<List<User>> {
        return try {
            val response = api.getUsers()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body()!!)
            } else {
                NetworkResponse.Error(Throwable())
            }
        } catch (e: Throwable) {
            NetworkResponse.Error(e)
        }
    }

    override suspend fun getUsersFromDatabase(): List<User>? {
        return usersDAO.getAll()

    }

    override suspend fun insertUsersFromDatabase(list: List<User>) {
        return usersDAO.insert(list)
    }

}