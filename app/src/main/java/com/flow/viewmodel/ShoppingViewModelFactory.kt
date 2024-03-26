package com.flow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flow.data.local.ShoppingDao

class ShoppingViewModelFactory(private val shoppingDao: ShoppingDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(shoppingDao) as T
    }
}