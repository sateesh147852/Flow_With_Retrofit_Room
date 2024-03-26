package com.flow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flow.data.remote.User
import com.flow.databinding.UserItemBinding

class UserAdapter(private var userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun updateData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val userItemBinding: UserItemBinding) :
        RecyclerView.ViewHolder(userItemBinding.root) {

        fun bind(user: User) {
            userItemBinding.apply {
                tvId.text = user.id.toString()
                tvName.text = user.name
                tvEmail.text = user.email
            }
        }
    }
}