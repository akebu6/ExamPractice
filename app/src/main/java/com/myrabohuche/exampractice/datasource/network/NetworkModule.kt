package com.myrabohuche.exampractice.datasource.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Named("submissionUrl")
    fun provideSubmissionUrl(): String {
        return "https://docs.google.com/forms/u/0/d/e/"
        //https://docs.google.com/forms/d/e/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("submissionUrl") baseUrl: String
    ): Retrofit.Builder {
        val okkHttpclient = OkHttpClient.Builder()
            .build()
        return Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit.Builder): ApiService {
        return retrofit
            .build()
            .create(ApiService::class.java)
    }


//    @Provides
//    @Singleton
//    fun provideApiService(moshi: Moshi, @Named("baseUrl") baseUrl: String): ApiService {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create(ApiService::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideSubmissionService(
//        moshi: Moshi,
//        @Named("submissionUrl") baseUrl: String
//    ): SubmissionService {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create(SubmissionService::class.java)
//    }

}
