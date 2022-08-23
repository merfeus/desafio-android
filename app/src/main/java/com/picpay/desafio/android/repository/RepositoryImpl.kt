package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.NetworkResponse
import com.picpay.desafio.android.service.PicPayService

class RepositoryImpl(private val api: PicPayService) : Repository {

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
}