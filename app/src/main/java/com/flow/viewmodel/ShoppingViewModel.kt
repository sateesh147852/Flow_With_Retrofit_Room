package com.flow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flow.data.local.ShoppingDao
import com.flow.model.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShoppingViewModel(private val shoppingDao: ShoppingDao) : ViewModel() {

    private val _sharedFlow = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val sharedFlow: StateFlow<List<ShoppingItem>> = _sharedFlow.asStateFlow()

    fun addItemsIntoDataBase(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingDao.insertShoppingItem(shoppingItem)
        }
    }

    fun getShoppingItems() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            shoppingDao.getAllShoppingItems().collect {
                _sharedFlow.value = it
            }
        }
    }

}