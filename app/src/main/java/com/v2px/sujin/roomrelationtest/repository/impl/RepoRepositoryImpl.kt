package com.v2px.sujin.roomrelationtest.repository.impl

import android.content.Context
import com.v2px.sujin.roomrelationtest.AppDatabase
import com.v2px.sujin.roomrelationtest.model.Repo
import com.v2px.sujin.roomrelationtest.repository.RepRepository
import io.reactivex.Flowable

/**
 * Created by sujin on 3/17/18.
 */

class RepoRepositoryImpl(context: Context) : RepRepository {

    private val instance = AppDatabase.getInstance(context)
    private val dao = instance.getUserRepoDAO()

    override fun getAllRepo(userId: Int): List<Repo>? {
        return dao.getRepos(userId)
    }

}