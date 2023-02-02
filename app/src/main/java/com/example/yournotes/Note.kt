package com.example.yournotes

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "headerText") val headerText: String,
    @ColumnInfo(name = "bodyText") val bodyText: String
): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}