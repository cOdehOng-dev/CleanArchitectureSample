package com.c0de_h0ng.cleansample.domain.repository

import com.c0de_h0ng.cleansample.data.local.RecentSearchDto
import com.c0de_h0ng.cleansample.data.remote.dto.UserDto
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by c0de_h0ng on 2022/01/21.
 */
interface GitHubRepository {

    // Remote
    suspend fun getUserList(searchWord: String): UserDto
    fun getRxUserList(searchWord: String): Observable<UserDto>

    // Local
    fun getRecentSearchList(): Flowable<List<RecentSearchDto>>
    fun insert(recentSearch: RecentSearchDto): Completable
    fun delete(recentSearch: RecentSearchDto): Completable
}