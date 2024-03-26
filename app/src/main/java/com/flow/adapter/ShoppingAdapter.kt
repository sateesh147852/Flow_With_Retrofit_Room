package com.flow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flow.databinding.ShoppingItemBinding
import com.flow.model.ShoppingItem

class ShoppingAdapter(private var shoppingList: List<ShoppingItem>) :
    RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder =
        ShoppingViewHolder(
            ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = shoppingList.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        holder.bind(shoppingList[position])
    }

    fun updateItem(shoppingList: List<ShoppingItem>) {
        this.shoppingList = shoppingList
        notifyDataSetChanged()
    }

    inner class ShoppingViewHolder(private val shoppingItemBinding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(shoppingItemBinding.root) {

        fun bind(shoppingItem: ShoppingItem) {
            shoppingItemBinding.apply {
                tvId.text = shoppingItem.id.toString()
                tvName.text = shoppingItem.itemName
                tvPrice.text = shoppingItem.price.toString()
                tvAmount.text = shoppingItem.amount.toString()
            }
        }

    }
}