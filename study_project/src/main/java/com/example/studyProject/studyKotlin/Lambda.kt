package com.example.studyProject.studyKotlin

import com.example.studyProject.studyKotlin.model.Book

//Lambda学习笔记
 object Lambda {
        fun test() {
            val getBook = ::Book
            getBook("123").name

            val map = listOf(
                Book("1"),
                Book("2")
            ).map{
                it.name==""
            }

            println(map)
        }
}
