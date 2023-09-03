package com.onBit.pixelDemo.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * 实体类
 *
 * @return
 * @author zhangxuyang
 * @create 2023/9/3
 **/
@Entity
data class RoomEntity(
    @PrimaryKey val id: String,
    val name: String,



) {
    override fun toString(): String {
        return "RoomEntity(id='$id', name='$name')"
    }
}