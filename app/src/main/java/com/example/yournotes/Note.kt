package com.example.yournotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "headerText") val headerText: String,
    @ColumnInfo(name = "bodyText") val bodyText: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}