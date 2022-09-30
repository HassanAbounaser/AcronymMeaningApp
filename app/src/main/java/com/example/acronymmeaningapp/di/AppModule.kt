package com.example.acronymmeaningapp.di

import android.content.Context
import com.example.acronymmeaningapp.data.remote.AcronymApiService
import com.example.acronymmeaningapp.data.remote.RemoteDataSourceImpl
import com.example.acronymmeaningapp.data.remote.interceptor.CachingInterceptor
import com.example.acronymmeaningapp.data.repository.RepositoryImpl
import com.example.acronymmeaningapp.domain.remote.RemoteDataSource
import com.example.acronymmeaningapp.domain.repository.Repository
import com.example.acronymmeaningapp.utils.Constants.BASE_URL
import com.example.acronymmeaningapp.utils.Constants.MAX_CACHE_SIZE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesOkhttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, MAX_CACHE_SIZE))
            .addNetworkInterceptor(CachingInterceptor())
            .build()
    }
    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): AcronymApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AcronymApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideRemoteDataSource(todoApiService: AcronymApiService ): RemoteDataSource {
        return RemoteDataSourceImpl(todoApiService)
    }
    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
    ): Repository {
        return RepositoryImpl(remoteDataSource)
    }
}