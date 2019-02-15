package com.jehutyno.blablacarmvvm.injection.module

import com.jehutyno.blablacarmvvm.network.BASE_URL
import com.jehutyno.blablacarmvvm.network.BlablacarApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideBlablacarApi(retrofit: Retrofit): BlablacarApi {
        return retrofit.create(BlablacarApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}