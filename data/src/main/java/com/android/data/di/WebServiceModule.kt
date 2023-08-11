package com.android.data.di

import android.content.Context
import com.android.data.BuildConfig
import com.android.data.api.PicSumApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WebServiceModule {

    private const val TIME_OUT = 30L
    private const val CACHE_SIZE: Long = 10 * 1024 * 1024

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext applicationContext: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(TIME_OUT, TimeUnit.MINUTES)
            //.addInterceptor(getLogInterceptor(BuildConfig.DEBUG, session))
            .cache(getCache(applicationContext)).build()
    }

    private fun getCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.PIC_SUM_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCheckoutApi(retrofit: Retrofit): PicSumApi {
        return retrofit.create(PicSumApi::class.java)
    }

//    private fun getLogInterceptor(isDebuggingEnabled: Boolean = false): Interceptor {
//        val builder = LoggingInterceptor.Builder()
//            .setLevel(if (isDebuggingEnabled) Level.BASIC else Level.NONE)
//            .log(Platform.INFO)
//            .tag("PICSUM")
//            .request("Request")
//            .response("Response")
//        builder.isDebugAble = isDebuggingEnabled
//        return builder.build()
//    }

}
