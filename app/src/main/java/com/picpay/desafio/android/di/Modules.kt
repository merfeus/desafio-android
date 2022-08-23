package com.picpay.desafio.android.di

import com.picpay.desafio.android.repository.Repository
import com.picpay.desafio.android.repository.RepositoryImpl
import com.picpay.desafio.android.service.RetrofitBuilder
import com.picpay.desafio.android.ui.users.ListOfUsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ListOfUsersViewModel(repository = get()) }
}

val repositoryModule = module {

    single<Repository> {
        RepositoryImpl(
            api = RetrofitBuilder.getAllUsers()
        )
    }
}