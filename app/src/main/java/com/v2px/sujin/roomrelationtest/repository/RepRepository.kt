package com.v2px.sujin.roomrelationtest.repository

import com.v2px.sujin.roomrelationtest.model.Repo
import io.reactivex.Flowable

/**
 * Created by sujin on 3/17/18.
 */
interface RepRepository {

    fun getAllRepo(userId: Int): List<Repo>?
}