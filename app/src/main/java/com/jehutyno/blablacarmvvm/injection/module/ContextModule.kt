package com.jehutyno.blablacarmvvm.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides



@Module
class ContextModule(private var context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}