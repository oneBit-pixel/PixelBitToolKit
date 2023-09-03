package com.onBit.pixelDemo.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onBit.pixelDemo.room.dao.RoomDao
import com.onBit.pixelDemo.room.entity.RoomEntity


@Database(entities = [RoomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): RoomDao

}