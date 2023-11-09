package com.onBit.pixelDemo.hit.entry

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MyEntryPoint {
    fun getRetrofit(): Retrofit
}