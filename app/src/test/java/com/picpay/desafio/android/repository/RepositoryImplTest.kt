package com.picpay.desafio.android.repository

import com.picpay.desafio.android.database.AppDatabase
import com.picpay.desafio.android.database.dao.UsersDAO
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RepositoryImplTest {

    private val apiService: PicPayService = mockk()

    private val userDao: UsersDAO = mockk()
    private val repositoryImpl = RepositoryImpl(apiService, userDao)

    @Test
    fun `when getUsers is called then it should call service getUsers`() {
        val user = User(img = "aaa", name = "Jose", id = 1, username = "Jose@aa")
        coEvery { apiService.getUsers() } returns Response.success(listOf(user))

        runBlockingTest {
            repositoryImpl.getUsers()
        }

//        assertEquals(Response.success(listOf(user)))

    }

    @Test
    fun `when getUsersFromDb is called then it should call users from db`() {
        val user = User(id = 1, img = "aaa", name = "Jose", username = "Jose@aa")
        coEvery { userDao.getAll() } returns listOf(user)

        runBlockingTest {
            RepositoryImpl(apiService, userDao).getUsersFromDatabase()
        }

        coVerify { userDao.getAll() }
    }

    @Test
    fun `when insertUser is called then it should call insert in db`() {
        val user = User(id = 2, img = "bbb", name = "Paulo", username = "Paulo@bbb")

        runBlockingTest {
            userDao.insert(listOf(user))
        }
        coVerify { userDao.insert(listOf(user)) }

    }
}