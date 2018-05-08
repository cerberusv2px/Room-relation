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
        return Flowable.fromCallable {
            dao.getAllUser().map {user ->
                user.repoList = dao.getRepos(user.id)
                user
            }
        }
                /*.map {
                    val repoList =  dao.getRepos(it.id)
                    it.repoList =  repoList
                    return@map it
                }*/
                /*.flatMap { userList ->
                    Flowable.fromIterable(userList)
                            .concatMap { user ->
                                dao.getRepos(user.id)?.map {
                                    user.repoList = it
                                    user
                                }
                            }.toList()
                    Flowable.just(userList)

                }*/


    }
}