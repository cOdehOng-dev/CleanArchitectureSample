package com.c0de_h0ng.cleansample.data.repository

import com.c0de_h0ng.cleansample.data.datasource.LocalDataSource
import com.c0de_h0ng.cleansample.data.datasource.RemoteDatSource
import com.c0de_h0ng.cleansample.data.local.RecentSearchDto
import com.c0de_h0ng.cleansample.data.remote.dto.UserDto
import com.c0de_h0ng.cleansample.domain.repository.GitHubRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/21.
 */
class GitHubRepositoryImpl @Inject constructor(
    private val remote: RemoteDatSource,
    private val local: LocalDataSource
) : GitHubRepository {

    override suspend fun getUserList(searchWord: String): UserDto {
        return remote.getUserList(searchWord)
    }

    override fun getRxUserList(searchWord: String): Observable<UserDto> {
        return remote.getRxUserList(searchWord).subscribeOn(Schedulers.io())
    }

    override fun getRecentSearchList(): Flowable<List<RecentSearchDto>> {
        return local.getRecentSearchList().subscribeOn(Schedulers.io())
    }

    override fun insert(recentSearch: RecentSearchDto): Completable {
        return local.insert(recentSearch).subscribeOn(Schedulers.io())
    }

    override fun delete(recentSearch: RecentSearchDto): Completable {
        return local.delete(recentSearch).subscribeOn(Schedulers.io())
    }

}