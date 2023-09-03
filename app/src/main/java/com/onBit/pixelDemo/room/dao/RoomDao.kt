package com.onBit.pixelDemo.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onBit.pixelDemo.room.entity.RoomEntity

/**
 * dao层 存一些方法 room自动实现
 *
 * @return
 * @author zhangxuyang
 * @create 2023/9/3
 **/

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(roomEntity: RoomEntity)

    @Query("SELECT * FROM roomentity Limit 50 OFFSET 50 ")
    fun selectAll(): List<RoomEntity>

}