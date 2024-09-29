package com.books.testtask.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.testtask.data.network.NetworkMonitor
import com.books.testtask.domen.entities.User
import com.books.testtask.domen.usecases.GetUsersFromInternetUseCase
import com.books.testtask.domen.usecases.GetUsersFromDatabaseUseCase

import com.books.testtask.domen.usecases.SetUsersToDatabaseUseCase
import com.books.testtask.presentation.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
        private val setUsersToDatabaseUseCase: SetUsersToDatabaseUseCase,
        private val getUsersFromDatabaseUseCase: GetUsersFromDatabaseUseCase,
        private val getUsersFromInternetUseCase: GetUsersFromInternetUseCase,
        private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _isLoading.value = true
        observeNetworkChanges()
        loadUsers()
    }

    private fun observeNetworkChanges() {
        networkMonitor.observeForever { isConnected ->
            _isLoading.value = true
            if (isConnected) {
                fetchUsers()
            } else {
                loadUsers()
            }
        }
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                getUsersFromInternetUseCase()
                        .catch {
                            _isLoading.value = false
                        }
                        .collect { userList ->
                    _users.value = userList
                            _isLoading.value = false
                    if (userList.isNotEmpty()) {
                        try {
                            setUsersToDatabaseUseCase.execute(userList)
                        } catch (e: Exception) {
                            _error.postValue("${Constants.ERROR_SAVING_TO_DB} ${e.message}")
                        }
                    }
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = Constants.ERROR_FETCHING_USERS
            }
        }
    }

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val usersFromDb = getUsersFromDatabaseUseCase()
                if (usersFromDb.isNotEmpty()) {
                    _isLoading.value = false
                    _users.postValue(usersFromDb)
                } else {
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _error.postValue("${Constants.ERROR_FETCHING_USERS} ${e.message}")
            }
        }
    }
}

