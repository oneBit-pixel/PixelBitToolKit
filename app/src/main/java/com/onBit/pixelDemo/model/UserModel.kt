package com.onBit.pixelDemo.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class UserModule {

    @Provides
    @ActivityScoped
    fun createUser():User{
        return User()
    }

}