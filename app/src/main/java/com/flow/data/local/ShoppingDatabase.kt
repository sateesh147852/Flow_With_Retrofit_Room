package com.flow.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flow.model.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    companion object {

        private var shoppingDatabase: ShoppingDatabase? = null

        fun getInstance(context: Context): ShoppingDatabase? {
            if (shoppingDatabase == null) {
                synchronized(this) {
                    shoppingDatabase =
                        Room.databaseBuilder(
                            context,
                            ShoppingDatabase::class.java,
                            "Shopping_Database"
                        ).build()
                }
            }
            return shoppingDatabase
        }
    }
}