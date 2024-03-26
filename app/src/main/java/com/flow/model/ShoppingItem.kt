package com.flow.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val price: Int,
    val amount: Float,
    val itemName: String
)
