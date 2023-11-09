package com.onBit.pixelDemo.hit.module

import com.blankj.utilcode.util.LogUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Qualifier


@Qualifier
annotation class ManType

@Qualifier
annotation class WomanType

@Module
@InstallIn(ActivityComponent::class)
object HumanModule {
    @ManType
    @Provides
    fun provideMan(man: Man): Human {
        return man
    }

    @WomanType
    @Provides
    fun provideWoman(woman: Woman): Human {
        return woman
    }

}

interface Human {
    fun sex()
}

class Man @Inject constructor() : Human {
    override fun sex() {
        LogUtils.d("我是男的")
    }

}

class Woman @Inject constructor() : Human {
    override fun sex() {
        LogUtils.d("我是女的")
    }

}