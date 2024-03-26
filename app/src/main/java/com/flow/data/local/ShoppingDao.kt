package com.flow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flow.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): Flow<List<ShoppingItem>>
}