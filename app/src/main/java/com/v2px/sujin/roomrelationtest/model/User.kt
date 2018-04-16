package com.v2px.sujin.roomrelationtest.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey


@Entity
data class User (
        @PrimaryKey var id: Int,
        var name: String,
        @Ignore var repoList: List<Repo>? = null
) {
    constructor() : this(0,"", null)
}