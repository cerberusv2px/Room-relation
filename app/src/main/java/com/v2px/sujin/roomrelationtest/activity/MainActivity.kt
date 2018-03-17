package com.v2px.sujin.roomrelationtest.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.v2px.sujin.roomrelationtest.AppDatabase
import com.v2px.sujin.roomrelationtest.R
import com.v2px.sujin.roomrelationtest.model.Repo
import com.v2px.sujin.roomrelationtest.model.User
import com.v2px.sujin.roomrelationtest.repository.impl.UserRepositoryImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val instance = AppDatabase.getInstance(this)
    private val dao = instance.getUserRepoDAO()

    private val userRepoImpl = UserRepositoryImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // insertData()
        displayData()
    }


    fun getData(): List<User> {
        return listOf(
                User(1, "Sujin",
                        listOf(
                                Repo(1, "Rx", 1),
                                Repo(2, "Room", 1))),

                User(2, "Amy",
                        listOf(
                                Repo(3, "Dagger", 2),
                                Repo(4, "Rx", 2)))
        )
    }

    fun insertData() {
        Observable
                .fromIterable(getData())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    dao.saveUser(it)
                    dao.saveRepos(it.repoList!!)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    println("user:$it")
                }
    }

    private fun displayData() {
        userRepoImpl.getAllUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { it }
                .subscribe {
                    println(it)
                }
    }
}
