package com.picpay.desafio.android.ui.users

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.ui.users.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListOfUsersFragment() : Fragment(R.layout.fragment_list_of_users) {

    private val viewModel by viewModel<ListOfUsersViewModel>()

    private val usersListAdapter = UserListAdapter()

    private lateinit var rvListOfUsers: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initViewModels()
        setupViewModels()
        setupRecyclerView()
    }

    private fun initViews(view: View) {
        rvListOfUsers = view.findViewById(R.id.rv_list_of_users)
        progressBar = view.findViewById(R.id.user_list_progress_bar)
    }

    private fun initViewModels() {

        viewModel.loadingStateLiveDate.observe(viewLifecycleOwner) {
            handleProgressBar(it)
        }

        viewModel.usersListing.observe(viewLifecycleOwner) {
            loadingUserData(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            showErrorMessage()
        }
    }

    private fun setupViewModels() {
        viewModel.getUsers()
    }

    private fun setupRecyclerView() {
        rvListOfUsers.apply {
            adapter = usersListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun handleProgressBar(state: ListOfUsersViewModel.State) {
        when (state) {
            ListOfUsersViewModel.State.LOADING -> {
                progressBar.isVisible = true
            }

            ListOfUsersViewModel.State.LOADING_FINISHED -> {
                progressBar.isVisible = false
            }
        }
    }

    private fun loadingUserData(list: List<User>) {
        onCharactersLoaded(list)
    }

    private fun showErrorMessage() {
        val message = getString(R.string.error)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun onCharactersLoaded(list: List<User>) {
        usersListAdapter.submitList(list)
    }

}