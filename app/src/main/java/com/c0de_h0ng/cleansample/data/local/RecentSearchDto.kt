package com.c0de_h0ng.cleansample.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
@Entity(tableName = "tbl_recent_search")
data class RecentSearchDto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "recentSearch")
    val recentSearch: String
)
