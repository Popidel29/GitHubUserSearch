package com.example.githubusersearch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusersearch.common.network.model.Repo
import com.example.githubusersearch.common.network.model.User
import com.example.githubusersearch.common.network.model.UserSearchModel
import com.example.githubusersearch.repositories.GitHubRemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class GitHubViewModel(private val repository: GitHubRemoteRepository) : ViewModel() {

    private val userListObservable = MutableLiveData<UserSearchModel>()
    private val userListErrorObservable = MutableLiveData<String>()
    private val userObservable = MutableLiveData<User>()
    private val userErrorObservable = MutableLiveData<String>()
    private val userReposObservable = MutableLiveData<List<Repo>>()
    private val userReposErrorObservable = MutableLiveData<String>()

    private lateinit var userSearchResponse: UserSearchModel
    private lateinit var singleItem: UserSearchModel

    fun getUserList(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userSearchResponse = repository.getUserList(username)

            withContext(Dispatchers.Main) {
                try {
                    val list = UserSearchModel.Item(1, "klj.png", "a", 1)
                    singleItem = UserSearchModel(listOf(list))
                    userListObservable.value = userSearchResponse
                } catch (e: Exception) {
                    userListErrorObservable.value = e.message
                }

            }
        }
    }

    fun getUser(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getUser(username)
            withContext(Dispatchers.Main) {
                try {
                    userObservable.value = response
                } catch (e: Exception) {
                    userErrorObservable.value = e.message
                } catch (e: HttpException) {
                    userErrorObservable.value = e.message
                } catch (e: Throwable) {
                    userErrorObservable.value = e.message
                }
            }
        }

    }

    fun getUserRepoByUser(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getRepositoriesByUser(username)
            withContext(Dispatchers.Main) {
                try {
                    userReposObservable.value = response
                } catch (e: Exception) {
                    userReposErrorObservable.value = e.message
                } catch (e: HttpException) {
                    userReposErrorObservable.value = e.message
                } catch (e: Throwable) {
                    userReposErrorObservable.value = e.message
                }
            }
        }

    }

    fun userListObservable(): LiveData<UserSearchModel> {
        return userListObservable
    }

    fun userListErrorObservable(): LiveData<String> {
        return userListErrorObservable
    }

    fun userObservable(): LiveData<User> {
        return userObservable
    }

    fun userErrorObservable(): LiveData<String> {
        return userErrorObservable
    }

    fun userReposObservable(): LiveData<List<Repo>> {
        return userReposObservable
    }

    fun userReposErrorObservable(): LiveData<String> {
        return userReposErrorObservable
    }
}