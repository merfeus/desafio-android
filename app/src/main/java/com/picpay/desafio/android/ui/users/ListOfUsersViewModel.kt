package com.picpay.desafio.android.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.Repository
import com.picpay.desafio.android.service.NetworkResponse
import kotlinx.coroutines.launch


class ListOfUsersViewModel(val repository: Repository) : ViewModel() {

    var loadingStateLiveDate = MutableLiveData<State>()

    private val _usersListing = MutableLiveData<List<User>>()
    val usersListing: LiveData<List<User>> = _usersListing

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    fun getUsers() {
        viewModelScope.launch {
            loadingStateLiveDate.value = State.LOADING
            when (val response = repository.getUsers()) {
                is NetworkResponse.Error -> {
                    _error.value = Unit
                }
                is NetworkResponse.Success -> {
                    _usersListing.value = response.data
                }
            }
            loadingStateLiveDate.value = State.LOADING_FINISHED
        }
    }

    enum class State {
        LOADING, LOADING_FINISHED
    }
}