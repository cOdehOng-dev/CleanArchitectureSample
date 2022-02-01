package com.c0de_h0ng.cleansample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
@Database(entities = [RecentSearchDto::class], version = 1)
abstract class RecentDatabase : RoomDatabase() {
    abstract fun recentSearchDao(): RecentSearchDao
}