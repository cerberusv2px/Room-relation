package com.v2px.sujin.roomrelationtest.repository.impl

import android.content.Context
import com.v2px.sujin.roomrelationtest.AppDatabase
import com.v2px.sujin.roomrelationtest.model.User
import com.v2px.sujin.roomrelationtest.repository.UserRepository
import io.reactivex.Flowable

/**
 * Created by sujin on 3/17/18.
 */
class UserRepositoryImpl(context: Context) : UserRepository {

    private val instance = AppDatabase.getInstance(context)
    private val dao = instance.getUserRepoDAO()

    override fun getAllUser(): Flowable<List<User>> {
        return dao.getAllUser()
                .map {
                    it.map {
                        val repoList =  dao.getRepos(it.id)
                        it.repoList =  repoList?.blockingFirst()
                        return@map it
                    }
                }
    }
}