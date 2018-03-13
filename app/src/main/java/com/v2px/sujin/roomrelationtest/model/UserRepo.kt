package com.v2px.sujin.roomrelationtest.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation


class UserRepo {

    @Embedded
    lateinit var user: User

    @Relation(parentColumn = "id", entityColumn = "user_id", entity = Repo::class)
    lateinit var repoList: List<Repo>


}