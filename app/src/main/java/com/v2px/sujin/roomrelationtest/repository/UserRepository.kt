package com.v2px.sujin.roomrelationtest.repository

import com.v2px.sujin.roomrelationtest.model.User
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by sujin on 3/17/18.
 */
interface UserRepository {

    fun getAllUser() : Flowable<List<User>>

}