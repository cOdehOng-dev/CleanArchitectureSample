package com.c0de_h0ng.cleansample.data.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM tbl_recent_search")
    fun getRecentSearchList(): Flowable<List<RecentSearchDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recentSearch: RecentSearchDto): Completable

    @Delete
    fun delete(recentSearch: RecentSearchDto): Completable
}