package com.v2px.sujin.roomrelationtest.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.v2px.sujin.roomrelationtest.model.Repo
import com.v2px.sujin.roomrelationtest.model.User
import io.reactivex.Flowable


@Dao
interface UserRepoDao {

    @Query("SELECT * from user WHERE id=:id")
    fun getUser(id: Int): User

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM repo where user_id=:userId")
    fun getRepos(userId: Int): List<Repo>?

    @Insert(onConflict = REPLACE)
    fun saveUser(user: User)

    @Insert(onConflict = REPLACE)
    fun saveUsers(userList: List<User>?)

    @Insert(onConflict = REPLACE)
    fun saveRepos(repoList: List<Repo>?)
}