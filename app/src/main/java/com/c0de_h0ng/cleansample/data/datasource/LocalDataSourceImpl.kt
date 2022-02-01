package com.c0de_h0ng.cleansample.data.datasource

import com.c0de_h0ng.cleansample.data.local.RecentSearchDao
import com.c0de_h0ng.cleansample.data.local.RecentSearchDto
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
class LocalDataSourceImpl @Inject constructor(
    private val dao: RecentSearchDao
) : LocalDataSource {
    override fun getRecentSearchList(): Flowable<List<RecentSearchDto>> = dao.getRecentSearchList()

    override fun insert(recentSearch: RecentSearchDto): Completable = dao.insert(recentSearch)

    override fun delete(recentSearch: RecentSearchDto): Completable = dao.delete(recentSearch)


}