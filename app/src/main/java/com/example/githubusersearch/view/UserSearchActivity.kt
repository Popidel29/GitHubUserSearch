package com.example.githubusersearch.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersearch.MyApp
import com.example.githubusersearch.R
import com.example.githubusersearch.common.INTENT_KEY
import com.example.githubusersearch.common.createToast
import com.example.githubusersearch.di.component.DaggerUserSearchComponent
import com.example.githubusersearch.di.module.GitHubViewModelModule
import com.example.githubusersearch.view.adapter.UserSearchAdapter

import com.example.githubusersearch.viewmodel.GitHubViewModel
import kotlinx.android.synthetic.main.activity_user_search.*
import javax.inject.Inject

class UserSearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: GitHubViewModel

    private lateinit var username: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_search)


        DaggerUserSearchComponent.builder().appComponent((application as MyApp).component())
            .gitHubViewModelModule(
                GitHubViewModelModule(this)
            ).build().inject(this)

        username = user_search

        user_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length != 0) {
                    viewModel.getUserList(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        if (username.text != null) {
            viewModel.userListObservable().observe(this, Observer { searchUser ->
                rv_user_search.layoutManager = LinearLayoutManager(this)
                rv_user_search.adapter = UserSearchAdapter(searchUser, object: OnUserRecyclerViewItemClicked{
                    override fun onUserItemClicked(username: String) {
                    val intent = Intent(this@UserSearchActivity, UserDetailActivity::class.java)
                        intent.putExtra(INTENT_KEY, username)
                        startActivity(intent)
                    }

                })

            })
            viewModel.userListErrorObservable().observe(this, Observer { error ->
                createToast(error)
            })
        }

    }
}
