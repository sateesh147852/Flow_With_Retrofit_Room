package com.flow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flow.data.remote.User
import com.flow.data.remote.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(private val userService: UserService) : ViewModel() {

    private var _stateFlow = MutableStateFlow<List<User>>(emptyList())
    val stateFlow = _stateFlow.asStateFlow()

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val result: Response<List<User>> = userService.getUsers()

            if (result.isSuccessful) {
                val responseBody = result.body()
                responseBody?.let {
                    _stateFlow.value = responseBody
                }
            }
        }
    }
}