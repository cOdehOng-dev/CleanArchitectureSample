package com.c0de_h0ng.cleansample.data.datasource

import com.c0de_h0ng.cleansample.data.local.RecentSearchDto
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
interface LocalDataSource {
    fun getRecentSearchList(): Flowable<List<RecentSearchDto>>
    fun insert(recentSearch: RecentSearchDto): Completable
    fun delete(recentSearch: RecentSearchDto): Completable
}