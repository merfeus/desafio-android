package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RepositoryImplTest {

    private val apiService: PicPayService = mockk()

    @Test
    fun `when getUsers is called then it should call service getUsers`() {
        val user = User(img = "aaa", name = "Jose", id = 1, username = "Jose@aa")
        coEvery { apiService.getUsers() } returns Response.success(listOf(user))

        runBlockingTest {
            RepositoryImpl(apiService).getUsers()
        }

        coVerify { apiService.getUsers() }

    }
}