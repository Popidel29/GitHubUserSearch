package com.example.githubusersearch.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersearch.R
import com.example.githubusersearch.common.loadImage
import com.example.githubusersearch.common.network.model.UserSearchModel
import com.example.githubusersearch.view.OnUserRecyclerViewItemClicked
import kotlinx.android.synthetic.main.search_user_item.view.*

class UserSearchAdapter(
    private val userList: UserSearchModel,
    private val onUserRecyclerViewItemClicked: OnUserRecyclerViewItemClicked
) : RecyclerView.Adapter<UserSearchAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_user_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.items.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        loadImage(userList.items[position].avatarUrl, holder.avatarImage)
        holder.username.text = userList.items[position].login
        holder.numberOfRepositories.text = userList.items[position].public_repos.toString()

        holder.bind(userList.items[position].login)
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.tv_search_user_username
        val avatarImage: ImageView = itemView.img_search_user
        val numberOfRepositories: TextView = itemView.tv_search_user_no_of_repositories

        fun bind(userName: String) {
            itemView.setOnClickListener {
                onUserRecyclerViewItemClicked.onUserItemClicked(userName)
            }
        }
    }
}