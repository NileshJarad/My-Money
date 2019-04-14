package com.n2ksp.expense_tracker.di.module

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class ContextModule(val context: Context){
    @Provides
    fun provideContext () :Context{
        return  context
    }

}
