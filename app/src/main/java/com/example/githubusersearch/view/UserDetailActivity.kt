package com.example.githubusersearch.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersearch.MyApp
import com.example.githubusersearch.R
import com.example.githubusersearch.common.INTENT_KEY
import com.example.githubusersearch.common.createToast
import com.example.githubusersearch.common.loadImage
import com.example.githubusersearch.common.network.model.Repo
import com.example.githubusersearch.di.component.DaggerUserDetailComponent
import com.example.githubusersearch.di.module.UserDetailViewModelModule
import com.example.githubusersearch.view.adapter.UserRepoAdapter
import com.example.githubusersearch.viewmodel.GitHubViewModel
import kotlinx.android.synthetic.main.activity_user_detail.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class UserDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: GitHubViewModel

    private lateinit var userRepo: List<Repo>

    private lateinit var adapter: UserRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        DaggerUserDetailComponent.builder().appComponent((application as MyApp).component())
            .userDetailViewModelModule(
                UserDetailViewModelModule(this)
            ).build().inject(this)

        val username = intent.getStringExtra(INTENT_KEY)

        viewModel.getUser(username)

        viewModel.userObservable().observe(this, Observer { userDetail ->
            loadImage(userDetail.avatarUrl, img_user_detail)
            tv_user_detail_username.text = userDetail.name
            tv_user_detail_email.text = userDetail.email
            tv_user_detail_location.text = userDetail.location
            tv_user_detail_join_date.text = userDetail.joinDate
            tv_user_detail_bio.text = userDetail.bio
            tv_user_detail_followers.text = userDetail.followers.toString()
            tv_user_detail_following.text = userDetail.following.toString()
        })
        viewModel.userErrorObservable().observe(this, Observer { error ->
            createToast(error)
        })

        viewModel.getUserRepoByUser(username)

        et_user_repository_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        viewModel.userReposObservable().observe(this, Observer { userRepos ->
            userRepo = userRepos
            adapter = UserRepoAdapter(userRepo, object : OnRepositoryViewItemClicked {
                override fun onRepoItemClicked(repoLink: String) {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(repoLink))
                    )
                }
            })
            rv_user_repositories.layoutManager = LinearLayoutManager(this)
            rv_user_repositories.adapter = adapter
        })

        viewModel.userReposErrorObservable().observe(this, Observer {
            createToast(it)
        })
    }

    private fun filter(text: String) {
        val filteredList:MutableList<Repo> = ArrayList()
        for (item in userRepo){
            if (item.name.toLowerCase(Locale.getDefault())
                    .contains(text.toLowerCase(Locale.getDefault()).trim())){
                filteredList.add(item)
            }
        }
        adapter.filterList(filteredList)
    }
}
