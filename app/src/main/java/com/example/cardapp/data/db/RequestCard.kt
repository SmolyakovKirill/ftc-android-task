package com.example.cardapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "requests")
data class RequestCard(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "scheme")
    var scheme: String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "country")
    var country: String,
    @ColumnInfo(name = "bank")
    var bank: String,
    @ColumnInfo(name = "prepaid")
    var prepaid: String,
)
