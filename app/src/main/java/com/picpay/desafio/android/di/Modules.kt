package com.picpay.desafio.android.di

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.database.AppDatabase
import com.picpay.desafio.android.database.dao.UsersDAO
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
            api = RetrofitBuilder.getAllUsers(),
            usersDAO = get()
        )
    }
}

val userDB = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, "user_app_db"
        )
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()

    }

    fun provideDao(database: AppDatabase): UsersDAO {
        return database.usersDAO
    }

    single { provideDatabase(application = androidApplication()) }
    single { provideDao(database = get()) }
}