package com.v2px.sujin.roomrelationtest.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey


@Entity(foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = CASCADE))
])
data class Repo(
        @PrimaryKey
        @ColumnInfo(name = "repo_id") var id: Int,
        @ColumnInfo(name = "repo_name") var name: String,
        @ColumnInfo(name = "user_id") var userId: Int
)