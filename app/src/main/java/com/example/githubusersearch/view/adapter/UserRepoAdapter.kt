package com.example.githubusersearch.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersearch.R
import com.example.githubusersearch.common.inflate
import com.example.githubusersearch.common.network.model.Repo
import com.example.githubusersearch.view.OnRepositoryViewItemClicked
import kotlinx.android.synthetic.main.user_repository_item.view.*

class UserRepoAdapter(
    var userRepo: List<Repo>,
    private val onRepoViewItemClicked: OnRepositoryViewItemClicked
) : RecyclerView.Adapter<UserRepoAdapter.UserRepoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        return UserRepoViewHolder(parent.inflate(R.layout.user_repository_item))
    }

    override fun getItemCount(): Int {
        return userRepo.size
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        holder.repoName.text = userRepo[position].name
        holder.forks.text = userRepo[position].forksCount.toString()
        holder.stars.text = userRepo[position].stargazersCount.toString()
        holder.bind("https://github.com/" + userRepo[position].htmlUrl)
    }

    inner class UserRepoViewHolder(itemVew: View) : RecyclerView.ViewHolder(itemVew) {
        val repoName: TextView = itemVew.tv_repository_name
        val forks: TextView = itemVew.tv_forks
        val stars: TextView = itemVew.tv_stars

        fun bind(repoLink: String) {
            itemView.setOnClickListener {
                onRepoViewItemClicked.onRepoItemClicked(repoLink)
            }
        }
    }
    fun filterList(filteredList: MutableList<Repo>) {
        userRepo = filteredList
        notifyDataSetChanged()
    }
}