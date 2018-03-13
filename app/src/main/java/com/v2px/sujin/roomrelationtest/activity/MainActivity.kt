package com.v2px.sujin.roomrelationtest.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.v2px.sujin.roomrelationtest.AppDatabase
import com.v2px.sujin.roomrelationtest.R
import com.v2px.sujin.roomrelationtest.model.Repo
import com.v2px.sujin.roomrelationtest.model.User
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val instance = AppDatabase.getInstance(this)
    val dao = instance.getUserRepoDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insertData()
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
}
