package com.flow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.flow.adapter.UserAdapter
import com.flow.data.remote.RetrofitModule
import com.flow.data.remote.User
import com.flow.databinding.ActivitySecondBinding
import com.flow.viewmodel.UserViewModel
import com.flow.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var userViewModel: UserViewModel
    private var userAdapter: UserAdapter = UserAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    private fun initViews() {
        binding.rvUsers.adapter = userAdapter
    }

    private fun initViewModel() {
        val userViewModelFactory = UserViewModelFactory(RetrofitModule.getUserService())
        userViewModel = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]
        userViewModel.getUser()

        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.stateFlow.collect {
                    updateAdapter(it)
                }
            }
        }
    }

    private fun updateAdapter(users: List<User>) {
        userAdapter.updateData(users)
    }
}