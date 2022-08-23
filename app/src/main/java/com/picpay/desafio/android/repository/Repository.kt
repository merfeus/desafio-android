package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.NetworkResponse

interface Repository {

    suspend fun getUsers(): NetworkResponse<List<User>>
}