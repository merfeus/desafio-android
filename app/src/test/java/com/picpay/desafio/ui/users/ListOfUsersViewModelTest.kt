package com.picpay.desafio.ui.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.RepositoryImpl
import com.picpay.desafio.android.service.NetworkResponse
import com.picpay.desafio.android.ui.users.ListOfUsersViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListOfUsersViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()


    private val testDispatcher = TestCoroutineDispatcher()
    private val repositoryImpl: RepositoryImpl = mockk()
    private val userObserver: Observer<List<User>> = mockk(relaxed = true)
    private val loadingObserver: Observer<ListOfUsersViewModel.State> = mockk(relaxed = true)
    private val errorObserver: Observer<Unit> = mockk(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getUsers is called then it should call repository to get all users`() {
        val user = User(img = "aaa", name = "Jose", id = 1, username = "Jose@aa")

        coEvery { repositoryImpl.getUsers() } returns NetworkResponse.Success(data = listOf(user))
        instantiate().getUsers()

        coVerify { repositoryImpl.getUsers() }
    }

    @Test
    fun `when repository takes one second to complete then loading should still be set as false after getUsers is updated`() {
        val user = User(img = "aaa", name = "Jose", id = 1, username = "Jose@aa")

        coEvery { repositoryImpl.getUsers() } coAnswers {
            delay(1000)
            NetworkResponse.Success(data = listOf(user))
        }
        coVerify { repositoryImpl.insertUsersFromDatabase(listOf(user)) }
        instantiate().getUsers()

        testDispatcher.advanceTimeBy(1000)

        coVerify {
            loadingObserver.onChanged(ListOfUsersViewModel.State.LOADING)
            repositoryImpl.getUsers()
            repositoryImpl.insertUsersFromDatabase(listOf(user))
            userObserver.onChanged(listOf(user))
            loadingObserver.onChanged(ListOfUsersViewModel.State.LOADING_FINISHED)
        }
    }

    @Test
    fun `when getUsers is called and has an error assign value to errorLiveData`() {

        coEvery { repositoryImpl.getUsers() } returns NetworkResponse.Error(Throwable())
        instantiate().getUsers()

        coVerify {
            loadingObserver.onChanged(ListOfUsersViewModel.State.LOADING)
            repositoryImpl.getUsers()
            errorObserver.onChanged(Unit)
            loadingObserver.onChanged(ListOfUsersViewModel.State.LOADING_FINISHED)
        }

    }

    @Test
    fun `when getUsers is called and has a success value for usersListingLiveData`() {
        val user = User(img = "aaa", name = "Jose", id = 1, username = "Jose@aa")

        coEvery { repositoryImpl.getUsers() } returns NetworkResponse.Success(listOf(user))
        instantiate().getUsers()

        coVerify {
            loadingObserver.onChanged(ListOfUsersViewModel.State.LOADING)
            repositoryImpl.getUsers()
            userObserver.onChanged(listOf(user))
            loadingObserver.onChanged(ListOfUsersViewModel.State.LOADING_FINISHED)
        }
    }

    @Test
    fun `when getUsers is called then it should call repository to get all users from db`(){
        val user = User(img = "aaa", name = "Jose", id = 1, username = "Jose@aa")

        coEvery { repositoryImpl.getUsersFromDatabase() } returns listOf(user)
        instantiate().getUsersFromDatabase()

        coVerify { repositoryImpl.getUsers() }
    }


    private fun instantiate(): ListOfUsersViewModel {
        val viewModel = ListOfUsersViewModel(repositoryImpl)
        viewModel.usersListing.observeForever(userObserver)
        viewModel.loadingStateLiveDate.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
        return viewModel
    }
}