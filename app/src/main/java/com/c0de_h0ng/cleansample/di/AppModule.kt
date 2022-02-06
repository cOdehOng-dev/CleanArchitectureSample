package com.c0de_h0ng.cleansample.di

import android.content.Context
import androidx.room.Room
import com.c0de_h0ng.cleansample.common.Constants.BASE_URL
import com.c0de_h0ng.cleansample.common.Constants.CONNECT_TIMEOUT
import com.c0de_h0ng.cleansample.common.Constants.READ_TIMEOUT
import com.c0de_h0ng.cleansample.common.Constants.WRITE_TIMEOUT
import com.c0de_h0ng.cleansample.data.datasource.LocalDataSource
import com.c0de_h0ng.cleansample.data.datasource.LocalDataSourceImpl
import com.c0de_h0ng.cleansample.data.datasource.RemoteDatSource
import com.c0de_h0ng.cleansample.data.datasource.RemoteDataSourceImpl
import com.c0de_h0ng.cleansample.data.local.RecentDatabase
import com.c0de_h0ng.cleansample.data.local.RecentSearchDao
import com.c0de_h0ng.cleansample.data.remote.GitHubApi
import com.c0de_h0ng.cleansample.data.repository.GitHubRepositoryImpl
import com.c0de_h0ng.cleansample.domain.repository.GitHubRepository
import com.c0de_h0ng.library.PrettyHttpLogging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by c0de_h0ng on 2022/01/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(PrettyHttpLogging())
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApi(okHttpClient: OkHttpClient): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRecentSearchDao(context: Context): RecentSearchDao {
        return Room.databaseBuilder(
            context,
            RecentDatabase::class.java,
            "recent.db"
        ).build().recentSearchDao()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: GitHubApi): RemoteDatSource {
        return RemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: RecentSearchDao): LocalDataSource {
        return LocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(remote: RemoteDatSource, local: LocalDataSource): GitHubRepository {
        return GitHubRepositoryImpl(remote, local)
    }

}