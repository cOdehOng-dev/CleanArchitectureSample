package com.c0de_h0ng.cleansample.di

import com.c0de_h0ng.cleansample.common.Constants.BASE_URL
import com.c0de_h0ng.cleansample.common.Constants.CONNECT_TIMEOUT
import com.c0de_h0ng.cleansample.common.Constants.READ_TIMEOUT
import com.c0de_h0ng.cleansample.common.Constants.WRITE_TIMEOUT
import com.c0de_h0ng.cleansample.common.PrettyHttpLogging
import com.c0de_h0ng.cleansample.data.datasource.RemoteDatSource
import com.c0de_h0ng.cleansample.data.datasource.RemoteDataSourceImpl
import com.c0de_h0ng.cleansample.data.remote.GitHubApi
import com.c0de_h0ng.cleansample.data.repository.GitHubRepositoryImpl
import com.c0de_h0ng.cleansample.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideRemoteDataSource(api: GitHubApi): RemoteDatSource {
        return RemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(remote: RemoteDatSource): GitHubRepository {
        return GitHubRepositoryImpl(remote)
    }

}